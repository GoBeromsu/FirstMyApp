package com.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ikovac.timepickerwithseconds.MyTimePickerDialog
import com.ikovac.timepickerwithseconds.TimePicker
import kotlinx.android.synthetic.main.activity_add.*
import java.time.LocalDate
import java.util.*


class AddActivity : AppCompatActivity() {




    var sumOfTime: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setTimeBtn.setOnClickListener{
//            addTime.text = Editable.Factory.getInstance().newEditable()
            setTimePicker()
        }

        addBtn.setOnClickListener {

            val replyIntent = Intent()
            val plan_time = addTime.text
            val plan_content = addContent.text

            if (TextUtils.isEmpty(plan_content) || TextUtils.isEmpty(plan_time)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                setResult(Activity.RESULT_OK, replyIntent.apply {
                    putExtra(content_REPLY, plan_content.toString())
                    putExtra(time_REPLY, plan_time.toString())
                })
            }
            finish()
        }
    }

    fun setTimePicker() {
        val timePicker = MyTimePickerDialog(
            this,
            MyTimePickerDialog.OnTimeSetListener() { timePicker: TimePicker, hoursOfDay: Int, minute: Int, seconds: Int ->
                sumOfTime=hoursOfDay * 60 * 60 + minute * 60 + seconds
                addTime.text= Editable.Factory.getInstance().newEditable(sumOfTime.toString())


            },
            Calendar.HOUR_OF_DAY,
            Calendar.MINUTE,
            Calendar.SECOND,
            true
        )


        timePicker.show()

    }

    companion object {
        const val content_REPLY = "content reply"
        const val time_REPLY = "time reply"
    }

}