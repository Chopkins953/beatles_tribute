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
import org.json.JSONObject
import java.net.URL


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class PlayFragment : Fragment() {

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

        var arguments = this.getArguments()
        var vid = arguments?.getSerializable("url") as String


//        var song = songList[position][0]
//        var origSong = song
//        song = song.replace(" ", "+")
//
//        var artist = "The Beatles"
//        var origArtist = artist
//        artist = artist.replace(" ", "+")
//
//        val keywords = artist + "+" + song
//        val max = 50
//
//        val string =
//            "https://www.googleapis.com/youtube/v3/search?part=snippet&q=$keywords&order=viewCount&maxResults=$max&type=video&videoCategory=Music&key=AIzaSyAJiIwkPJA1F7Xmy_jZobS0KRYA2ystamE"
//

        var helper = Helper(vid)
        var thread = Thread(helper) //this is something I didn't do in slideshow
        thread.start()

    }

    inner class Helper : Runnable {
        private var url: String = ""


        constructor(url: String) {
            this.url = url
        }

        override public fun run() //reading the data
        {

//            var selected_video = ""
//            var selected_title = ""
//            val data = URL(url).readText() //this is the line that will throw an exception if you dont put it in the helper thread
//            println(data)
//
//            //takes in a properly formatted string and returns the formatted dictionary
//            var json = JSONObject(data)
//
//            //this returns the items array
//            var items = json.getJSONArray("items") // this is the "items: [ ] part
//
//            var channels = ArrayList<String>()
//            var titles = ArrayList<String>()
//            var videos = ArrayList<String>()
//            var best = false
//
//            //this loop will parse any JSON from youtube
//            for (i in 0 until items.length())
//            {
//                //Get the ith item
//                var videoObject = items.getJSONObject(i)
//
//                //Extracth the id Hashmap
//                var idDict = videoObject.getJSONObject("id")
//
//                //Get the videoid using videoId key
//                var videoId = idDict.getString("videoId")
//                println(videoId)
//                //Get the snippet Hashmap
//                var snippetDict = videoObject.getJSONObject("snippet")
//                //Get the title
//                var title =  snippetDict.getString("title")
//
//                var channel = snippetDict.getString("channelTitle")
//
//
//                //Add the titles to the lists
//                channels.add(channel)
//                titles.add(title)
//                videos.add(videoId)
//            }
//
//            for (i in 0 until titles.size)
//            {
//                if(titles[i].contains(song) && channels[i].contains(artist))
//                {
//                    selected_video = videos[i]
//                    break
//                }
//                else if (titles[i].contains(song) && titles[i].contains(artist))
//                {
//                    selected_video = videos[i]
//                    break
//                }
//
//            }
//
//            if(selected_video.equals(""))
//            {
//
//            }

            var helper1 = UIThreadHelper(url)
            MainActivity.getInstance().runOnUiThread(helper1)
//
//        }
        }

        inner class UIThreadHelper : Runnable {
            private var video: String = ""

            constructor(video: String) {
                this.video = video
            }

            override fun run() {


                val settings = web.getSettings()
                settings.setJavaScriptEnabled(true)
                settings.setDomStorageEnabled(true)
                settings.setMinimumFontSize(10)
                settings.setLoadWithOverviewMode(true)
                settings.setUseWideViewPort(true)
                settings.setBuiltInZoomControls(true)
                settings.setDisplayZoomControls(false)
                web.setVerticalScrollBarEnabled(false)
                settings.setDomStorageEnabled(true)
                web.setWebChromeClient(WebChromeClient())
                var str = "https://www.youtube.com/watch?v=" + video
                web.loadUrl(str)

            }
        }
    }

    class WebDelagate : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            println("started")
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            println("finish")
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            println(error.description)
        }

        override fun onReceivedHttpError(
            view: WebView, request: WebResourceRequest, errorResponse: WebResourceResponse
        ) {
            println(errorResponse.data)
        }

        override fun onReceivedSslError(
            view: WebView, handler: SslErrorHandler,
            error: SslError
        ) {
            println(error.primaryError)
        }
    }
}