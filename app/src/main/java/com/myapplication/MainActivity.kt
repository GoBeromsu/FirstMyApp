package com.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.Database.Plan
import com.myapplication.Database.PlanAdapter
import com.myapplication.Database.PlanDB
import com.myapplication.Database.PlanViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var planViewModel: PlanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // 어댑터 정의
        val adapter = PlanAdapter(this)
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
            startActivity(intent)

        }
    }


}


