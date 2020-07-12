package com.movieme.moviergb.info;

import android.os.AsyncTask;
import android.util.Log;

import com.movieme.moviergb.model.MovieInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static com.movieme.moviergb.api.Kobis.getApiKey;
import static com.movieme.moviergb.api.Kobis.getSearchMovieInfo;

public class MovieInfoParser extends AsyncTask<Void, Void, Void> {

    MovieInfo movieInfo;
    public MovieInfo target;

    String movieCode = null;
    String tagName;
    StringBuffer buffer = null;

    // 웹사이트에 연결하기 위해 url 클래스 적용
    static URL url;

    // 생성자
    public MovieInfoParser() {

    }
    public MovieInfoParser(String movieCode) {
        this.movieCode = movieCode;
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
            url = new URL(getSearchMovieInfo() + "?key=" + getApiKey() + "&movieCd=" + movieCode);
            Log.d("[URL]", String.valueOf(url));

            // 웹 사이트를 통해서 읽어드린 XML 문서를 안드로이드에 저장
            InputStream in = url.openStream();

            // XML 문서를 읽고 파싱하는 객체에 넘겨주면서 인코딩 UTF-8 지정
            xpp.setInput(in, "UTF-8");
            int directorCount = 0;

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
                        Log.d("[MovieInfoParser]", String.valueOf(xpp.getName()));
                        if (tagName.equals("movieInfo")) {
                            // 영화상세정보 객체 생성
                            movieInfo = new MovieInfo();
                        } else if (tagName.equals("showTm")) {
                            xpp.next();
                            movieInfo.setShowTm(xpp.getText());
                        } else if (tagName.equals("peopleNm") && directorCount == 0) {
                            xpp.next();
                            movieInfo.setDirector(xpp.getText());
                            directorCount++;
                        } else if (tagName.equals("companyNm")) {
                            xpp.next();
                            movieInfo.setCompany(xpp.getText());
                        } else if (tagName.equals("watchGradeNm")) {
                            xpp.next();
                            movieInfo.setAudit(xpp.getText());
                        } else if (tagName.equals("movieNm")) {
                            xpp.next();
                            movieInfo.setMovieName(xpp.getText());
                        } else if (tagName.equals("openDt")) {
                            xpp.next();
                            movieInfo.setOpenDate(xpp.getText());
                        } else if (tagName.equals("nationNm")) {
                            xpp.next();
                            movieInfo.setNation(xpp.getText());
                        } else if (tagName.equals("genreNm")) {
                            xpp.next();
                            movieInfo.setGenre(xpp.getText());
                        } else if (tagName.equals("typeNm")) {
                            xpp.next();
                            movieInfo.setTypeName(xpp.getText());
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tagName = xpp.getName();
                        if (tagName.equals("movieInfo")) {
                            target = movieInfo;
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
