package com.example.shayariapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shayariapp.Model.ShayriModel
import com.example.shayariapp.adapter.AllShayriAdapter
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_all_shyri.*

class allShyriActivity : AppCompatActivity() {

    lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_shyri)

        val name = intent.getStringExtra("name")
        val id = intent.getStringExtra("id")

        btnBack.setOnClickListener {
            onBackPressed()
        }

        CategoryName.text = name.toString()

        db = FirebaseFirestore.getInstance()

//        as there are many data inside document
//        we get to know which shayri we've to display by it's id
        if (id != null) {
            db.collection("Shayri").document(id).collection("All")
                .addSnapshotListener{ value ,error ->
                    val Shayrilist = arrayListOf<ShayriModel>()
                    val data = value?.toObjects(ShayriModel::class.java)
                    Shayrilist.addAll(data!!)

                    rcvAllShayri.layoutManager = LinearLayoutManager(this)
                    rcvAllShayri.adapter = AllShayriAdapter(this,Shayrilist)
            }
        }

    }
}