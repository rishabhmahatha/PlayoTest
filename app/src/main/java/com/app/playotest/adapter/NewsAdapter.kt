package com.app.playotest.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.app.playotest.R
import com.app.playotest.model.DataModel

class NewsAdapter(
    private val context: Context, private val arrNewsList: ArrayList<DataModel.HitModel>,
    click: ItemClick
) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    var itemClick: ItemClick = click

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView? = itemView.findViewById(R.id.row_data_news_title)
        var tvAuthor: TextView? = itemView.findViewById(R.id.row_data_news_author)
        var tvDate: TextView? = itemView.findViewById(R.id.row_data_news_date)
        var llParent: LinearLayout? = itemView.findViewById(R.id.row_data_news_llParent)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_data_news,
            parent,
            false
        ) as View
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle!!.text = arrNewsList.get(position).title
        holder.tvAuthor!!.text = arrNewsList.get(position).author
        holder.tvDate!!.text = arrNewsList.get(position).createdAt


        holder.llParent!!.setOnClickListener {
            itemClick.onNewsClick(arrNewsList[position], it)
        }
    }

    override fun getItemCount() = arrNewsList.size

    interface ItemClick {

        fun onNewsClick(model: DataModel.HitModel, view: View)

    }

}