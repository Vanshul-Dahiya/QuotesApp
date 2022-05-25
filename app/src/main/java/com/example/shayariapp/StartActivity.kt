package com.example.shayariapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btnStart.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        btnRate.setOnClickListener{

                val uri = Uri.parse("market://details?id=$packageName")
            val myAppLinkToMarket =  Intent(Intent.ACTION_VIEW, uri)
            try {
                    startActivity(myAppLinkToMarket)
                } catch (e : ActivityNotFoundException) {
                    Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show()
                }
        }

        btnMore.setOnClickListener{
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
            }
        }

    }
}