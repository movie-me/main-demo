package com.movieme.moviergb;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MovieInfo extends AsyncTask<Void, Void, Void> {

    // Context Path 설정
    private String CONTEXT_PATH = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie";
    private String SEARCH_MOVIE_INFO = "/searchMovieInfo.xml";

    String keyword;
    String tagName;
    StringBuffer buffer = null;

    // 제대로 데이터가 읽어졌는지를 판단해주는 변수
    boolean flag = false;

    // AsyncTask는 쓰레드 관리와 UI Thread와의 통신은 원활하게 도와주는 Wrapper Class이다.
    // 쓰레드를 쓰기위해서 AsyncTask클래스를 상속받음

    // 웹사이트에 연결하기 위해 url 클래스 적용
    static URL url;

    // xml에서 읽어드려서 저장할 변수
    String movieNm = "";

    Movie movie;
    ArrayList<Movie> items = new ArrayList<Movie>();

    public MovieInfo() {

    }

    public MovieInfo(String code) {
        this.keyword = keyword;
        this.items = items;
        this.flag = flag;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // XML 문서를 읽고 파싱하는 객체 선언
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            // 네임스페이스 사용 여부
            factory.setNamespaceAware(true);

            // 실제 sax 형태로 데이터를 파싱하는 객체 선언
            XmlPullParser xpp = factory.newPullParser();

            // 웹사이트에 접속
            url = new URL(CONTEXT_PATH + SEARCH_MOVIE_INFO + "?key=84860e7bae0b07af4c7c7ff379be1997" + "&movieCd=" + "20124079");

            Log.d("[URL]", String.valueOf(url));
            //웹사이트를 통해서 읽어드린 xml문서를 안드로이드에 저장
            InputStream in = url.openStream();

            //xml문서를 일고 파싱하는 객체에 넘겨줌
            xpp.setInput(in, "UTF-8"); // xml문서의 인코딩 정확히 지정

            //item 태그 안이라면
            boolean isInItemTag = false;

            //이벤트 타입을 얻어옴
            int eventType = xpp.getEventType();

            //문서의 끝까지 읽어 드리면서 title과 descripton을 추출해냄
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        //Log.d("mytag","Start document");
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        //Log.d("mytag","End document");
                        break;
                    case XmlPullParser.START_TAG:
                        // 태그명 읽기
                        tagName = xpp.getName();
                        if (tagName.equals("movie")) {
                            // 영화 객체 생성
                            movie = new Movie();
                        } else if (tagName.equals("movieCd")) {
                            eventType = xpp.next();
                            movie.setMovieCd(xpp.getText());
                        } else if (tagName.equals("movieNm")) {
                            eventType = xpp.next();
                            movie.setMovieNm(xpp.getText());
                        } else if (tagName.equals("openDt")) {
                            eventType = xpp.next();
                            movie.setOpenDt(xpp.getText());
                        } else if (tagName.equals("typeNm")) {
                            eventType = xpp.next();
                            movie.setTypeNm(xpp.getText());
                        } else if (tagName.equals("nationAlt")) {
                            eventType = xpp.next();
                            movie.setNationAlt(xpp.getText());
                        } else if (tagName.equals("genreAlt")) {
                            eventType = xpp.next();
                            movie.setGenreAlt(xpp.getText());
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tagName = xpp.getName();
                        if (tagName.equals("movie")) {
                            items.add(movie);
                        }
                        break;
                }

                eventType = xpp.next();
            }
            // 모든 데이터가 저장되었다면,
            flag = true; // true : 지정된 xml파일을 읽고 필요한 데이터를 추출해서 저장 완료된 상태
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
