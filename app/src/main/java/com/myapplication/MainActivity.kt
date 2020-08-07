package com.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.Database.Plan
import com.myapplication.Database.PlanAdapter
import com.myapplication.Database.PlanViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ButtonClickListener {

    private lateinit var planViewModel: PlanViewModel
    private lateinit var adapter:PlanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // 어댑터 정의
        adapter = PlanAdapter(this,this)
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

    override fun deleteBtnClicked(plan: Plan) {
        planViewModel.delete(plan)
    }
}


