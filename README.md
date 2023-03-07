
# Keyworld Article Page




## API, spec.

* https://docs.google.com/spreadsheets/d/10GJkZGWwnGIZg4BjjDkF3nrkFK9s3uPav97MdIULcdc/edit#gid=0
* spreadsheet 보다 Intellij 하단 Endpoints 에서 보면 각각의 api에 대한 request/response 가 자세히 나와있어 보기 수월할듯함





## Installation

Intellij IDEA ultimate - jetbrains에서 학생 무료지원 가능
Community에서도 열리긴 할텐데 안되는 기능들 있을지는 몰?루

```bash
  Clone -> Keyworld-Article-Page open -> src/main/java/com/keyworld/projectboard/KeyworldProjectBoardApplication -> run
  하단 탭에서 services -> Add service -> run configuration type -> spring boot 
  running에 KeyworldProjectBoardApplication 떠 있으면 localhost/8080 
  안열려있으면 에러뜬거임
  Services의 Console 에 auto generated security password가 있을 것임.
  Login 창이 뜨면 user / {generated password} 치고 들어가면 됨 -> 추후 관리자 페이지용 인증 추가 예정
```

##  

* /build/resources/main 에 html이랑 css directory 갈아엎고 react작업 저기서 하면 되는듯함
* login, kakaoAuth 만들어놓긴 했는데 필요없대서 뺄예정 -> 완료
* hashtag 검색 기능도 해놓긴 했는데 필요없어보이면 뺄예정 -> 완료
