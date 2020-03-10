package com.example.thebeatles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{
    private var people = MainFragment.getAlbums()


    override fun getItemCount(): Int
    {
        return MainFragment.getAlbums().size
    }

    //This creates a ViewHolder object based on card_layout for each cell
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(v)
    }

    //This initializes the viewHolder to whatever the data source specifies
    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.itemTitle.text = people[position][2]
        holder.itemDetail.text = people[position][0] //title
        holder.itemImage.setImageResource(MainActivity.getInstance().resources.getIdentifier(people[position][3],"drawable",
            MainActivity.getInstance().packageName))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        init
        {
            itemImage = itemView.findViewById(R.id.imageView)
            itemTitle = itemView.findViewById(R.id.name)
            itemDetail = itemView.findViewById(R.id.title)


            var handler = Handler()
            itemView.setOnClickListener(handler)

        }

        //run when one of the cells is clicked
        inner class Handler() : View.OnClickListener
        {
            override fun onClick(v: View?)
            {
                val itemPosition = getLayoutPosition()
                var albumList = MainFragment.getAlbums()
                var songList = MainFragment.getSongList(albumList[itemPosition][3])

                //Get the navigation controller
                var navController = Navigation.findNavController(AlbumFragment.getInstance().view!!)

                val bundle = Bundle()
                bundle.putSerializable("album", songList)
                navController.navigate(R.id.albumToSongs, bundle)
            }
        }
    }




}