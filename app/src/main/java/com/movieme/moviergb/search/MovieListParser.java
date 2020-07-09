package com.movieme.moviergb.search;

import android.os.AsyncTask;
import android.util.Log;

import com.movieme.moviergb.model.Movie;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.movieme.moviergb.api.Kobis.getApiKey;
import static com.movieme.moviergb.api.Kobis.getSearchMovieList;

// 쓰레드를 사용하기 위해 AsyncTask를 상속받고 UI Thread와의 통신을 원활하게 도와주는 Wrapper Class 역할 담당
public class MovieListParser extends AsyncTask<Void, Void, Void> {

    String keyword;
    String tagName;

    // 웹사이트에 연결하기 위해 url 클래스 적용
    static URL url;

    Movie movie;
    ArrayList<Movie> items = new ArrayList<>();

    // 생성자
    public MovieListParser() {
    }
    public MovieListParser(String keyword) {
        this.keyword = keyword;
        this.items = items;
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

            // 웹 사이트에 접속
            url = new URL(getSearchMovieList() + "?key=" + getApiKey() + "&movieNm=" + keyword);

            Log.d("[URL]", String.valueOf(url));

            // 웹 사이트를 통해서 읽어드린 XML 문서를 안드로이드에 저장
            InputStream in = url.openStream();

            // XML 문서를 읽고 파싱하는 객체에 넘겨주면서 인코딩 UTF-8 지정
            xpp.setInput(in, "UTF-8");

            // 이벤트 타입을 얻어옴
            int eventType = xpp.getEventType();

            // traversing 하면서 필요한 정보 추출
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        // Log.d("[MovieList][START_DOC]", "Start document");
                        break;
                    case XmlPullParser.START_TAG:
                        // 태그명 읽기
                        tagName = xpp.getName();
                        if (tagName.equals("movie")) {
                            // 영화 객체 생성
                            movie = new Movie();
                        } else if (tagName.equals("movieCd")) {
                            xpp.next();
                            movie.setMovieCd(xpp.getText());
                        } else if (tagName.equals("movieNm")) {
                            xpp.next();
                            movie.setMovieNm(xpp.getText());
                        } else if (tagName.equals("openDt")) {
                            xpp.next();
                            movie.setOpenDt(xpp.getText());
                        } else if (tagName.equals("typeNm")) {
                            xpp.next();
                            movie.setTypeNm(xpp.getText());
                        } else if (tagName.equals("nationAlt")) {
                            xpp.next();
                            movie.setNationAlt(xpp.getText());
                        } else if (tagName.equals("genreAlt")) {
                            xpp.next();
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

                    case XmlPullParser.END_DOCUMENT:
                        // Log.d("[MovieList][END_DOC]", "End document");
                        break;
                }
                // 다음 단위 읽기
                eventType = xpp.next();
            }

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