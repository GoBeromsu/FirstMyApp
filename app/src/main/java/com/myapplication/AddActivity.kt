package com.myapplication

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.Database.Plan
import com.myapplication.Database.PlanDB
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity: AppCompatActivity() {

    private var planDB:PlanDB?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        addBtn.setOnClickListener {

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

}