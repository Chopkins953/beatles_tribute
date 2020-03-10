package com.example.thebeatles


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class ConfigFragment : Fragment() {


    companion object
    {
        private var urls = arrayListOf("http://static.france24.com/live/F24_EN_LO_HLS/live_web.m3u8",
            "http://weather-lh.akamaihd.net/i/twc_1@92006/master.m3u8",
            "http://cbsnewshd-lh.akamaihd.net/i/CBSNHD_7@199302/master.m3u8",
            "https://edge.free-speech-tv-live.top.comcast.net/out/u/fstv.m3u8",
            "http://media4.tripsmarter.com:1935/LiveTV/ACVBHD/chucklist.m3u8",
            "http://na-all15.secdn.net/pegstream3-live/play/c3e1e4c4-7f11-4a54-8b8f-c590a95b4ade/playlist.m3u8",
            "http://oflash.dfw.swagit.com/live/daytonabeachfl/smil:std-4x3-1-a/chucklist.m3u8",
            "http://video.oct.dc.gov/out/u/DCN.m3u8",
            "http://d3ktuc8v2sjk6m.cloudfront.net/livetv/ngrp:HouseChannel_all/chucklist.m3u8",
            "http://173.199.158.79:1935/roku/myStream/playlist.m3u8",
            "http://otv3.ocfl.net:1936/OrangeTV/smil:OrangeTV.smil/chunklist_w1007974604_b894100_sleng.m3u8",
            "http://video.seminolecountyfl.gov:1935/live/SGTV/chunklist.m3u8",
            "http://tstv-stream.tsm.utexas.edu/hls/livestream_hi/index.m3u8",
            "http://diffusionm4.assnat.qc.ca/canal9/250.sdp/playlist.m3u8"
        )
        private var callLetters = arrayListOf("France24","Weather","CBS","Free Speech", "Travel", "SECDN", "Daytona", "HouseTV","ROKU","Orange County", "Seminole County",
            "University of Texas","University of California")
        private var instance : AlbumFragment? = null
        public fun getInstance() : AlbumFragment
        {
            return instance!!
        }
        public fun getUrls() : ArrayList<String>
        {
            return urls
        }
        public fun updateUrl(s : String)
        {
            urls.add(s)
        }
        public fun getCallLetters() : ArrayList<String>
        {
            return callLetters
        }
        public fun updateCallLetters(s : String)
        {
            callLetters.add(s)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        instance = this
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var handler = AlbumHandler()

        urlText.setOnEditorActionListener(handler)
        urlText.addTextChangedListener(handler)

        callText.setOnEditorActionListener(handler)
        callText.addTextChangedListener(handler)

        submit.setOnClickListener(handler)
        //testing compainon object update by changing the text view

    }

}
class Handler : View.OnClickListener, TextView.OnEditorActionListener, TextWatcher
{
    override fun onClick(p0: View?) {

        var url = AlbumFragment.getInstance().urlText.getText()
        var call = AlbumFragment.getInstance().callText.getText()

        if(url.toString() != "" || call.toString() != "")
        {
            //change arrays here
            AlbumFragment.updateUrl(url.toString())
            AlbumFragment.updateCallLetters(call.toString())
        }

    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean
    {
        println(v?.getText())
        var im = MainActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        im.hideSoftInputFromWindow(v?.getWindowToken(),0) //gets rid of virtual keyboard
        return true
    }

    override fun afterTextChanged(s: Editable?)
    {
        println(s)
    }
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)
    {
        println(s)
    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int)
    {
        println(s)
    }

}
