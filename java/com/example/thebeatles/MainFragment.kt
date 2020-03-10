package com.example.thebeatles


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import java.io.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var covers = arrayListOf("intro1", "pleasepleaseme", "with_the_beatles", "harddaysnight",
                                    "beatlesforsale" ,"help", "rubber_soul", "revolver", "sgt_pepper",
                                    "white", "yellowsubmarine", "abbeyroad", "letitbe", "magicalmysterytour",
                                    "pastmastersvolume1", "pastmastersvolume2")

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    companion object
    {
        private var instance : MainFragment? = null
        public fun getInstance() : MainFragment
        {
            return instance!!
        }
        public fun getAlbums() : Array<Array<String>>
        {
            var is1 = MainActivity.getInstance().getAssets().open("albums.txt")
            var reader1 = BufferedReader(InputStreamReader(is1))
            var lines = reader1.readLines()
            var  arrayLines1 = lines.toTypedArray()
            var allData1 = arrayOf<Array<String>>()//creates a 2d array of strings
            //Parse into fields
            for (i in 0..arrayLines1.size -1)
            {
                var array1 = arrayLines1[i].split("^")
                allData1 +=  array1.toTypedArray()
            }
            return allData1
        }

        public fun getSongList(song : String) : Array<Array<String>>
        {
            var fn = song + ".txt"
            var is1 = MainActivity.getInstance().getAssets().open(fn)
            var reader1 = BufferedReader(InputStreamReader(is1))
            var lines = reader1.readLines()
            var  arrayLines1 = lines.toTypedArray()
            var allData1 = arrayOf<Array<String>>()//creates a 2d array of strings
            //Parse into fields
            for (i in 0..arrayLines1.size -1)
            {
                var array1 = arrayLines1[i].split("^")
                allData1 +=  array1.toTypedArray()
            }
            return allData1
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
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        var id = MainActivity.getInstance().resources.getIdentifier("image2", "drawable", MainActivity.getInstance().packageName)
        imageView.setImageResource(id)

        var helper = Helper(3)
        var thread = Thread(helper) //this is something I didn't do in slideshow
        thread.start()




    }
    override fun onStop()
    {
        super.onStop()

    }
    inner class Helper : Runnable
    {
        private var duration : Long = 0


        constructor(duration : Long)
        {
            this.duration = duration
        }

        override public fun run()
        {
            var count = 0


            while(true)
            {
                Thread.sleep(duration * 1000) //Delay
                // this thread updates the text and image view
                var fn = covers[count]
                var t = Updater(fn)
                MainActivity.getInstance().runOnUiThread(t)

                count++
                if(count > 15)
                {
                    count = 0
                }
            }
        }
    }
    inner class Updater : Runnable
    {
        private var fn : String = ""

        constructor (s : String)
        {
            fn = s
        }
        override  fun run()
        {

            var image = MainFragment.getInstance().imageView
            if(image != null) {
                var id = MainActivity.getInstance().resources.getIdentifier(
                    fn,
                    "drawable",
                    MainActivity.getInstance().packageName
                )
                image.setImageResource(id)
            }
        }
    }
}



