package com.example.thebeatles


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import kotlinx.android.synthetic.main.fragment_web.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class WebFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web, container, false)
    }
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = this.getArguments()
        var url = arguments ?.getString ("url")


        var webDelegate = WebDelagate()
        webView.settings.userAgentString = "Android WebView"
        webView.settings.allowContentAccess = true
        webView.settings.blockNetworkImage = false
        webView.settings.allowUniversalAccessFromFileURLs = true
        webView.settings.allowFileAccessFromFileURLs = true
        webView.settings.loadsImagesAutomatically = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.blockNetworkLoads = false
        webView.settings.allowContentAccess = true
        webView.settings.setAppCacheEnabled(true)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.setSupportZoom(true)
        webView.settings.setAppCacheEnabled(true)
        webView.settings.safeBrowsingEnabled = true
        webView.settings.useWideViewPort = true
        webView.webViewClient = webDelegate
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl(url)
    }

}

class Delegate : WebViewClient()
{
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?)
    {
        super.onPageStarted(view, url, favicon)
        println("started")
    }
    override fun onPageFinished(view: WebView, url: String)
    {
        super.onPageFinished(view,url)
        println("finish")
    }
    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError)
    {
        println(error.description )
    }
    override fun onReceivedHttpError(
        view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse
    )
    {
        println(errorResponse.data)
    }
    override fun onReceivedSslError(
        view: WebView, handler: SslErrorHandler,
        error: SslError
    )
    {
        println(error.primaryError)
    }
}
