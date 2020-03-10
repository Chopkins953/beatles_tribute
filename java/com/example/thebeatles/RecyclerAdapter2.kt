package com.example.thebeatles

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.net.URL

class RecyclerAdapter2( val songList : Array<Array<String>>) : RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = songList[position][1]
        holder.itemDetail.text = songList[position][0]
    }


    override fun getItemCount(): Int {
        return songList.size
    }

    //This creates a ViewHolder object based on card_layout for each cell
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemTitle: TextView
        var itemDetail: TextView

        init {

            itemTitle = itemView.findViewById(R.id.name)
            itemDetail = itemView.findViewById(R.id.title)


            var handler = Handler()
            itemView.setOnClickListener(handler)

        }

        //run when one of the cells is clicked
        inner class Handler() : View.OnClickListener, DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (p1 == DialogInterface.BUTTON_NEGATIVE) {
                    println("negative")
                } else if (p1 == DialogInterface.BUTTON_POSITIVE) {
                    println("positive")
                }
            }

            override fun onClick(v: View?) {

                var song = songList[position][0]
                var origSong = song
                song = song.replace(" ", "+")

                var artist = "The Beatles"
                var origArtist = artist
                artist = artist.replace(" ", "+")

                val keywords = artist + "+" + song
                val max = 50

                val string =
                    "https://www.googleapis.com/youtube/v3/search?part=snippet&q=$keywords&order=viewCount&maxResults=$max&type=video&videoCategory=Music&key=AIzaSyAJiIwkPJA1F7Xmy_jZobS0KRYA2ystamE"


                var helper = Helper(string, origSong, origArtist)
                var thread = Thread(helper) //this is something I didn't do in slideshow
                thread.start()

            }

            inner class Helper : Runnable {
                private var url: String = ""
                private var song: String = ""
                private var artist: String = ""

                constructor(url: String, song: String, artist: String) {
                    this.url = url
                    this.song = song
                    this.artist = artist
                }

                override public fun run() //reading the data
                {

                    var selected_video = ""
                    val data = URL(url).readText() //this is the line that will throw an exception if you dont put it in the helper thread
                    //println(data)

                    //takes in a properly formatted string and returns the formatted dictionary
                    var json = JSONObject(data)

                    //this returns the items array
                    var items = json.getJSONArray("items") // this is the "items: [ ] part

                    var channels = ArrayList<String>()
                    var titles = ArrayList<String>()
                    var videos = ArrayList<String>()

                    //this loop will parse any JSON from youtube
                    for (i in 0 until items.length()) {
                        //Get the ith item
                        var videoObject = items.getJSONObject(i)

                        //Extracth the id Hashmap
                        var idDict = videoObject.getJSONObject("id")

                        //Get the videoid using videoId key
                        var videoId = idDict.getString("videoId")
                        println(videoId)
                        //Get the snippet Hashmap
                        var snippetDict = videoObject.getJSONObject("snippet")
                        //Get the title
                        var title = snippetDict.getString("title")

                        var channel = snippetDict.getString("channelTitle")


                        //Add the titles to the lists
                        channels.add(channel)
                        titles.add(title)
                        videos.add(videoId)
                    }

                    for (i in 0 until titles.size) {
                        if (titles[i].contains(song, ignoreCase = true) && channels[i].contains(artist, ignoreCase = true)) {
                            selected_video = videos[i]
                            break
                        } else if (titles[i].contains(song, ignoreCase = true) && titles[i].contains(artist, ignoreCase = true)) {
                            selected_video = videos[i]
                            break
                        }

                    }


                    if(!selected_video.equals("")) {
                        var navController = Navigation.findNavController(SongsFragment.getInstance().view!!)
                        val bundle = Bundle()
                        bundle.putSerializable("url", selected_video)
                        navController.navigate(R.id.songsToWeb, bundle)
                    }
                    else
                    {
//                        //code for dialogue box goes here
//                        var handler = Handler()
//                        val dialogBuilder = AlertDialog.Builder(MainActivity.getInstance())
//                        dialogBuilder.setMessage("Not Connected To Internet")
//                        //handler is the thing than handles the event that something is pressed
//                        dialogBuilder.setPositiveButton("OK",handler)
//                        val alert1 = dialogBuilder.create()
//                        alert1.setTitle("No Internet")
//                        alert1.show()
                    }

                }
            }
        }


    }
}