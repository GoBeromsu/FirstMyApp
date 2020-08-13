package com.myapplication

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.AddActivity.Companion.content_REPLY
import com.myapplication.AddActivity.Companion.time_REPLY
import com.myapplication.Database.Plan
import com.myapplication.Database.PlanAdapter
import com.myapplication.Database.PlanViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), DeleteBtnListener {
    private val AddActivityRequestCode = 1


    private lateinit var planViewModel: PlanViewModel
    private lateinit var adapter: PlanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        supportActionBar?.title = Html.fromHtml("<font color='#ffffff'>The Timer </font>")


        // 어댑터 정의
        adapter = PlanAdapter(this, this)
        // 어댑터와 recycler view 연결
        mRecyclerView.adapter = adapter
        // 레이아웃 매니저 설정
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setHasFixedSize(true)
        // viewModelProvider로 모델 받아오기
        planViewModel = ViewModelProvider(this).get(PlanViewModel::class.java)

        //
        planViewModel.allPlan.observe(this, Observer { plan ->
            plan?.let { adapter.setPlans(it) }
        })

        mAddBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivityForResult(intent, AddActivityRequestCode)

        }
    }




    override fun deleteBtnClicked(plan: Plan) {
        planViewModel.delete(plan)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == AddActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->

                val content = data.getStringExtra(content_REPLY)!!
                val time = data.getStringExtra(time_REPLY)!!.toInt()

                val plan = Plan(content, time)
                planViewModel.insert(plan)
                Unit
            }
        }
        if (requestCode == AddActivityRequestCode && resultCode == Activity.RESULT_OK) {
        } else {
            Toast.makeText(
                applicationContext,
                "Plan is not saved It is Empty",
                Toast.LENGTH_LONG
            ).show()
        }
    }


}



