package com.example.shayariapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shayariapp.Model.CategoryModel
import com.example.shayariapp.adapter.CategoryAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = FirebaseFirestore.getInstance()

        db.collection("Shayri").addSnapshotListener{value,error ->
//            make a list and then pass to recylerview

            val list = arrayListOf<CategoryModel>()

//            get data now
            val data = value?.toObjects(CategoryModel::class.java)
            list.addAll(data!!)

            recVCategory.layoutManager = LinearLayoutManager(this)
            recVCategory.adapter = CategoryAdapter(this,list)
        }

//        val list = arrayListOf<String>("Sed shyyri", "hindi shyyri","rom-com shyrri")



        btnMenu.setOnClickListener {
            if(drawer_layout.isDrawerOpen(Gravity.LEFT)){
               drawer_layout.closeDrawer(Gravity.LEFT)
            }else{
               drawer_layout.openDrawer(Gravity.LEFT)
            }
        }

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.share ->{
                    try {
                        val shareIntent = Intent(Intent.ACTION_SEND)
                        shareIntent.type = "text/plain"
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                        var shareMessage = "\nLet me recommend you this application\n\n"
                        shareMessage =
                            """
                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}    
                            """.trimIndent()
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                        startActivity(Intent.createChooser(shareIntent, "choose one"))
                    } catch (e: Exception) {
                        //e.toString();
                    }
                    true
                }
                R.id.more -> {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
                    } catch (e: ActivityNotFoundException) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
                    }
                    true
                }
                R.id.rate -> {
                    val uri = Uri.parse("market://details?id=$packageName")
                    val myAppLinkToMarket =  Intent(Intent.ACTION_VIEW, uri)
                    try {
                        startActivity(myAppLinkToMarket)
                    } catch (e : ActivityNotFoundException) {
                        Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show()
                    }
                    true
                }
                else -> false
            }

        }
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(Gravity.LEFT)){
           drawer_layout.closeDrawer(Gravity.LEFT)
        }
        else{super.onBackPressed()}
    }
}