package com.movieme.moviergb.api;

public class Kobis {

    private static final String API_KEY = "84860e7bae0b07af4c7c7ff379be1997";

    // 기본 Context Path 설정
    private static final String CONTEXT_PATH = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie";

    // 영화정보 조회 API
    private static final String SEARCH_MOVIE_LIST = CONTEXT_PATH + "/searchMovieList.xml";

    // 영화상세정보 조회 API
    private static final String SEARCH_MOVIE_INFO = CONTEXT_PATH + "/searchMovieInfo.xml";

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getSearchMovieList() {
        return SEARCH_MOVIE_LIST;
    }

    public static String getSearchMovieInfo() {
        return SEARCH_MOVIE_INFO;
    }

}
