package com.movieme.moviergb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // 멤버변수 설정
    EditText editText;
    Button button;

    // 발급 받은 API 키
    String apiKey = "84860e7bae0b07af4c7c7ff379be1997";

//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    String dateStr = sdf.format("20190101");

    ArrayAdapter adapter;
    ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 멤버변수 초기화
        editText = findViewById(R.id.search_edittext);
        button = findViewById(R.id.search_btn);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
//        listView.setAdapter(adapter);
    }

    // xml 문서 파싱
    public void clickBtn(View view) {
        new Thread() {
            @Override
            public void run() {
                items.clear();

                Date date = new Date();//현재 날짜와 시간을 가진 객체
                date.setTime(date.getTime() - (1000 * 60 * 60 * 24)); //현재 날짜의 1일을 뺀 날짜

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //SimpleDateFormat 자동으로 편리하게 포맷을 넣을 수 있다.
                String dateStr = sdf.format(date);

                String address =
//                        "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.xml" +
                        "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml" +
                                "?key=" + "84860e7bae0b07af4c7c7ff379be1997" +
                                "&targetDt=" + "20190101";

                // 위 xml 문서 주소(address)에 스트림을 연결하여 데이터를 읽어오기
                try {
                    URL url = new URL(address);

                    // Stream 열기
                    // is는 바이트 스트림이라 문자열로 받기위해 isr이 필요하고 isr을 pullparser에게 줘야 함; 바이트스트림
                    InputStream is = url.openStream();

                    // 문자 스트림으로 변환
                    InputStreamReader isr = new InputStreamReader(is);

                    // 읽어들인 XML 문서를 분석(parse)해주는 객체 생성
                    // XMLPullParser 를 만들려면 Factory가 필요해서 팩토리 만들고 XMLPullParser 생성 후 isr 연결
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(isr);

                    //xpp를 이용해서 xml 문서를 분석

                    // xpp.next();
                    // XmlPullParser는 시작부터 문서의 시작점에 있으므로 next해주면 START_DOCUMENT를 못 만난다.
                    int eventType = xpp.getEventType();

                    String tagName;
                    StringBuffer buffer = null;

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                runOnUiThread(new Runnable() {  //여기는 별도 스레드이므로 화면 구성을 하려면 runOnUiThread 필요
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "파싱을 시작했다.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;

                            case XmlPullParser.START_TAG:
                                tagName = xpp.getName();
                                if (tagName.equals("dailyBoxOffice")) {
                                    buffer = new StringBuffer();
                                } else if (tagName.equals("rank")) {
                                    buffer.append("순위:");
                                    xpp.next();
                                    // 아래 두줄을 한줄로 줄일 수 있다.
                                    // String text = xpp.getText();
                                    // buffer.append(text + "\n");
                                    buffer.append(xpp.getText() + "\n");
                                } else if (tagName.equals("movieNm")) {
                                    buffer.append("제목:");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");
                                } else if (tagName.equals("openDt")) {
                                    buffer.append("개봉일:");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");
                                } else if (tagName.equals("audiAcc")) {
                                    buffer.append("누적관객수:");
                                    xpp.next();
                                    buffer.append(xpp.getText() + "\n");
                                }
                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                tagName = xpp.getName();
                                if (tagName.equals("dailyBoxOffice")) {
                                    items.add(buffer.toString());
                                    // 리스트뷰 갱신
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter.notifyDataSetChanged();
                                        }
                                    });

                                }
                                break;
                        }

                        // 두줄을 한줄로 쓸 수 있다.
                        // xpp.next();
                        // eventType = xpp.getEventType();

                        eventType = xpp.next();
                    }   // end of while loop

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "파싱종료!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, ListActivity.class);
                            startActivity(intent);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } finally {
                }
            } // run() ..
        }.start();
    }

}
