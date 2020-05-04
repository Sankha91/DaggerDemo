package com.sankha.daggerdemo2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.sankha.daggerdemo2.db.WordEntity

class ViewListAdapter(private val wordList : ArrayList<WordEntity>, private val context: Context) : RecyclerView.Adapter<ViewListAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.view_row_item, parent, false))
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val wordEntity = wordList.get(position)
        with(holder){
            title.text = wordEntity.message
            timestamp.text = wordEntity.timeStamp
            delete.setOnClickListener {

            }
        }
    }

    class CustomViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview) {
        val title = itemview.findViewById<AppCompatTextView>(R.id.tvMsg)
        val timestamp = itemview.findViewById<AppCompatTextView>(R.id.tvTimestamp)
        val delete = itemview.findViewById<AppCompatImageView>(R.id.imgvwDelete)
    }
}