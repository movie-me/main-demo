# Movie RGB

## Open API 활용 사이트
영화관입장권통합전산망 Open API 활용
- [영화목록 조회 API](http://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do)
- [영화상세정보 조회 API](http://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do)

## Movie RGB 디렉토리 구조
총 3개의 Activity로 구성
- MainActivty: 초기화면에서 찾고자 하는 영화 검색
- ListActivty: 영화 리스트 조회
- DetailMainActivty: 영화 상세보기 화면

## Get Started 
- manifest 추가
- build.gradle (Module: app)
  - androidx.recyclerview:recyclerview 추가
