package com.example.webbrowser

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.URLUtil
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import androidx.core.widget.ContentLoadingProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {

    private val progressBar : ContentLoadingProgressBar by lazy {
        findViewById(R.id.progressBar)
    }
    private val refreshLayout : SwipeRefreshLayout by lazy {
        findViewById(R.id.refreshLayout)
    }
    private val webView: WebView by lazy {
        findViewById(R.id.webView)
    }
    private val addressBar: EditText by lazy {
        findViewById(R.id.addressBar)
    }
    private val homeButton: ImageButton by lazy {
        findViewById(R.id.homeButton)
    }
    private val backButton: ImageButton by lazy {
        findViewById(R.id.backButton)
    }
    private val forwardButton: ImageButton by lazy {
        findViewById(R.id.forwardButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        bindViews()
    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else{
        super.onBackPressed()
        }
    }
    @SuppressLint("SetJavaScriptEnabled") // 경고 무시
    private fun initViews(){
        webView.apply { // apply 를 사용하면 webView 가 this 로 전달 된다
            webViewClient = WebViewClient() // 외부 브라우저가 아닌 내부 브라우저로 가져오기위한 코드
            webChromeClient = WebChromeClient() // 로딩 진행상황 확인
            settings.javaScriptEnabled = true // 자바 스크립트를 수행 할 수 있기 위한 코드
            loadUrl(DEFAULT_URL)
        }
    }
    private fun bindViews(){

        homeButton.setOnClickListener {
            webView.loadUrl(DEFAULT_URL)
        }
        addressBar.setOnEditorActionListener{ v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                val loadingUrl = v.text.toString()

                if(URLUtil.isNetworkUrl(loadingUrl)){
                    webView.loadUrl(loadingUrl)
                }else{
                    webView.loadUrl("http://$loadingUrl")
                }

            }
            return@setOnEditorActionListener false
        }
        backButton.setOnClickListener {
            webView.goBack()
        }
        forwardButton.setOnClickListener {
            webView.goForward()
        }
        refreshLayout.setOnRefreshListener {
            webView.reload()
        }
    }
    inner class WebViewClient: android.webkit.WebViewClient(){

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.show()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            refreshLayout.isRefreshing = false
            progressBar.hide()
            backButton.isEnabled = webView.canGoBack() // 뒤로갈 히스토리가 없으면 비활성화
            forwardButton.isEnabled = webView.canGoForward()
            addressBar.setText(url)
        }
    }
    inner class WebChromeClient : android.webkit.WebChromeClient(){
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)

            progressBar.progress = newProgress
        }
    }
    companion object{
        private const val DEFAULT_URL = "http://google.com" // 디폴트 URL
    }
}