package com.example.thebeatles


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_album.*


/**
 * A simple [Fragment] subclass.
 *
 */
class SongsFragment : Fragment()
{
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>? = null
    companion object
    {
        private var instance : SongsFragment? = null
        public fun getInstance() : SongsFragment
        {
            return instance!!
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        instance = this
        var arguments = this.getArguments()
        var songs = arguments?.getSerializable("album") as Array<Array<String>>



        layoutManager = LinearLayoutManager(MainActivity.getInstance())
        recycler_view.layoutManager = layoutManager
        adapter = RecyclerAdapter2(songs)
        recycler_view.adapter = adapter

    }


}
