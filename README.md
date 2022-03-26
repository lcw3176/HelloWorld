# MapshotServer
## 소개
Mapshot 서비스의 프록시 서버로 활용되고 있습니다. 현재 용도는 카카오 지도를 대신 받아오는 역할을 하고 있습니다.

## uri
|uri|method|params|기능|
|---|---|--|------|
|/map/kakao|GET||지도 생성이 가능한지 응답|
||POST|KakaoMap.class|지도 생성 후 이미지 바이트 리턴|
|/map/kakao/crawl|GET|KakaoMap.class|옵션에 맞는 지도 이미지 리턴|

## 구현 상황
### BackEnd
- domain
    - map 
        - kakao
            - controller
                - KakaoMapController.class (유저한테 정보 받아오기, 유저 순차 처리)
                - MapCrawlerController.class (지도 가져오기)
            - dto
                - KakaoMap.class (카카오 지도 관련 정보)
            - error
                - KakaoMapAdvice.class (kakao 에러 관리)
                - KakaoMapError.class (에러 코드, 메세지 관리)
            - service
                - CaptureService.class (MapCrawlerController에서 이미지 가져오기)
            - customClass
                - CustomChromeDriver.class (풀 스크린샷 관련 정의한 커스텀 클래스)
            - config
                - ChromeDrvierConfig.class (크롬 드라이버 관련 Bean 정의)
- global
    - common
        - UriInfo.class (서버 uri들 정리)
    - dto
        - ErrorMessage.class (에러 메세지 형식)
    - routine
        - ~~AlwaysWakeUpserver.class (헤로쿠 슬립 방지)~~
    - util
        - QueryGenerator.class (카카오 지도 관련 정보 쿼리스트링 변환기)
- infra
    - sns
        - util
            - SlackMessageUtil.class (슬랙 메세지 포맷 형식 변환기)
        - IMessageClient.interface (메세지 클라이언트 인터페이스)
        - SlackClient.class (IMessageClient 슬랙 구현체)

### Backend 클래스 다이어그램
![class](https://user-images.githubusercontent.com/59993347/151492486-b8fb4a0b-5ab6-4b0f-af85-72adfe904c78.png)

### FrontEnd
- index.html (웰컴 페이지)
- map
    - kakao.html (옵션에 맞는 이미지 지도 동적 로딩)

### 이전 예정
- 헤로쿠 타임아웃 빈도수가 꽤 높다
    - 헤로쿠 30초 타임아웃 제한 에러가 어떻게 발생하는지를 모르겠다
    - 에러 로그가 슬랙으로 와도 실제로는 이미지 전송이 정상적으로 이루어진 경우가 많아서, 작동이 된건지 만 건지 종잡을 수가 없다.
- 오라클 무료 클라우드 서버로 이전 예정.
    - 적어도 타임아웃 제한에서는 벗어날 것으로 예상.

### 기록사항
- 파이어폭스로 변경 시도

