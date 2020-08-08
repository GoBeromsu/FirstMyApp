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
            val plan_time = addTime.text
            val plan_content = addContent.text

            if(TextUtils.isEmpty(plan_content)||TextUtils.isEmpty(plan_time)){
                setResult(Activity.RESULT_CANCELED,replyIntent)
            }else{
                setResult(Activity.RESULT_OK, replyIntent.apply {
                    putExtra(content_REPLY, plan_content.toString())
                    putExtra(time_REPLY, plan_time.toString())
                })
            }
            finish()
        }
    }

    companion object {
        const val content_REPLY = "content reply"
        const val time_REPLY = "time reply"
    }

}