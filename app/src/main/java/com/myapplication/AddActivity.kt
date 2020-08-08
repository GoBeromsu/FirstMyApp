package com.myapplication

import android.app.Activity
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.text.TextUtils
import android.util.Log.d
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.Database.Plan
import com.myapplication.Database.PlanDB
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        addBtn.setOnClickListener {

            val replyIntent = Intent()
            val plan_time = addTime.text.toString()
            val plan_content = addContent.text.toString()


            setResult(Activity.RESULT_OK, replyIntent.apply {
                putExtra(content_REPLY, plan_content)
                putExtra(time_REPLY, plan_time)
            })
            finish()
        }
    }

    companion object {
        const val content_REPLY = "content reply"
        const val time_REPLY = "time reply"
    }

}