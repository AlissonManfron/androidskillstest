package br.com.cinq.androidtestcinq.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.cinq.androidtestcinq.R
import br.com.cinq.androidtestcinq.service.Album
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter(val albuns: ArrayList<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder{
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun getItemCount() = albuns.size

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.txt_name_album.text = albuns[position].title
    }

    class AlbumViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val txt_name_album: TextView = itemView.txt_name_album
    }

}