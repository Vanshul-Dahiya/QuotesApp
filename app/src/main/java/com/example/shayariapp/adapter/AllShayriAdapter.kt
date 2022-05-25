package com.example.shayariapp.adapter

import android.content.ActivityNotFoundException
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.shayariapp.BuildConfig
import com.example.shayariapp.Model.ShayriModel
import com.example.shayariapp.R
import com.example.shayariapp.allShyriActivity
import kotlinx.android.synthetic.main.item_category.view.*
import kotlinx.android.synthetic.main.item_shayri.view.*


class AllShayriAdapter(
    val AllShyriActivity: allShyriActivity,
    val Shayrilist: ArrayList<ShayriModel>,
) : RecyclerView.Adapter<AllShayriAdapter.ShayriViewHolder>() {

    class ShayriViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    val colorList = arrayListOf<String>("#81ecec","#00cec9","#ff7675")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShayriViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shayri,parent,false)
        return ShayriViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShayriViewHolder, position: Int) {

        when {
            position % 3 == 0 -> {
                holder.itemView.mainBackGround.setBackgroundColor(R.drawable.gradient)
            }
            position % 3 == 1 -> {
                holder.itemView.mainBackGround.setBackgroundColor(R.drawable.gradient_2)
            }
            position % 3 == 2 -> {
                holder.itemView.mainBackGround.setBackgroundColor(R.drawable.gradient_3)
            }
        }

        holder.itemView.itemShayri.text = Shayrilist[position].data.toString()

        holder.itemView.shareBtn.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n ${Shayrilist[position].data} \n\n"
                shareMessage =
                    """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}    
                            """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                AllShyriActivity.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }

        holder.itemView.copyBtn.setOnClickListener{
            val clipboard: ClipboardManager? =
               AllShyriActivity.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager?
            val clip = ClipData.newPlainText("label", Shayrilist[position].data.toString())
            clipboard?.setPrimaryClip(clip)

            Toast.makeText(AllShyriActivity ,"Copied Successfully", Toast.LENGTH_SHORT).show()
        }

        holder.itemView.wtspBtn.setOnClickListener{
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, Shayrilist[position].data.toString())
            try {
                AllShyriActivity.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {

            }
        }

    }

    override fun getItemCount(): Int {
       return Shayrilist.size
    }

}