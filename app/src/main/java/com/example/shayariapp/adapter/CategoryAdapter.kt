package com.example.shayariapp.adapter

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shayariapp.MainActivity
import com.example.shayariapp.Model.CategoryModel
import com.example.shayariapp.R
import com.example.shayariapp.allShyriActivity
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CategoryModel>)
    : RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    val colorList = arrayListOf<String>("#81ecec","#00cec9","#ff7675")
    class CatViewHolder( itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = LayoutInflater.from(mainActivity).inflate(R.layout.item_category,parent,false)
        return CatViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {

//        setting different background color
        when {
            position%3 == 0 -> {
                holder.itemView.itemText.setBackgroundColor(Color.parseColor(colorList[0]))
            }
            position%3 == 1 -> {
                holder.itemView.itemText.setBackgroundColor(Color.parseColor(colorList[1]))
            }
            position%3 == 2 -> {
                holder.itemView.itemText.setBackgroundColor(Color.parseColor(colorList[2]))
            }
        }

        holder.itemView.itemText.text = list[position].name.toString()

        holder.itemView.CategoryLayoutID.setOnClickListener {
            val intent = Intent(mainActivity,allShyriActivity::class.java)
//            put data also i.e name and id
            intent.putExtra("id",list[position].id)
            intent.putExtra("name",list[position].name)
            mainActivity.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}