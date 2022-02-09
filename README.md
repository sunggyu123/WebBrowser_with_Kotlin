# WebBrowser_with_Kotlin

## Simple Web Browser 

▶웹사이트를 불러올수있다.

▶뒤로가기, 앞으로 가기 기능 구현

▶홈버튼 기능 구현

▶로딩정도 확인가능 기능 구현

▶##보안관련 작업은 대강하고 진행##


▶ConstraintLayout

▶EditText

▶WebView

## 1. 기본 UI구성하기

▶vector asset 으로 홈,뒤로가기,앞으로가기 그림 추가

## 2. URL 로딩 기능 구현하기

▶ERR_CLEARTEXT_NOT_PERMITTED 
	-> 보안 관련 에러 //manifest에 android:usesCleartextTraffic="true" 추가하면 해결 

▶webView.webViewClient = WebViewClient() // 외부 브라우저가 아닌 내부 브라우저로 가져오기위한 코드

▶webView.settings.javaScriptEnabled = true // 자바 스크립트를 수행 할 수 있기 위한 코드

▶IME_ACTION_DONE 을 사용하여 이동시 자동 키보드 내림 구현

▶setOnEditorActionListener 기능 사용 // action 기능 

## 3. 네비게이션 기능 구현하기

▶onBackPressed() 로 뒤로가기 버튼을 눌렀을시 더이상 뒤로 갈 데이터가 없으면 앱이 종료 아니면 뒤로가지는 기능 구현

## 4. 완성도 높이기

▶android:background="?attr/selectableItemBackground" 로 리플이펙트 구현

▶app:layout_constraintDimensionRatio="1:1"로 버튼 영역을 확보하였다.

▶url 입력창의 모양을 수정

▶elevation , background 를 수정하여 그림자를 만듬

▶종속 항목을 선언 gradle 안에 dependencies 에
   implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
   추가

▶ SwifeRefreshLayout을 사용하여 하단으로 드래그시 새로고침 기능 구현

▶ 새로고침후 새로고침 모양이 사라지지 않는것을 해결하기위해 
    
    page가 로딩이 끝난후 동작 할 수 있도록 class를 지정하고 
    
    onPageClient 를 override 하여 로딩이 끝남을 사용하고 
    
    isRefreshing = false로 해결

▶ contentLoadingLayout으로 페이지 로딩 상황 게이지 구현

▶selectAllonForcus 로 전체 주소가 드래그되있기를 희망하여 구현

▶페이지가 이동하면 내가 입력한 주소가 아닌 이동한 페이지의 주소가 나타나게 구현

▶URLUtill.isNetworkUrl 을 사용하여 http:// 가 자동으로 나타나게 구현

## 5. 완성

![구글](https://user-images.githubusercontent.com/72656002/153123894-3ef4faf6-00ca-4335-a6c8-54275c85121e.PNG)
