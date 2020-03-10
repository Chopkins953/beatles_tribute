package com.example.thebeatles


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    companion object
    {
        private var instance : MainFragment? = null
        public fun getInstance() : MainFragment
        {
            return instance!!
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
                var fn = "image" + (count + 1).toString()
                var t = Updater(fn)
                MainActivity.getInstance().runOnUiThread(t)



                count++
                if(count > 2)
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



