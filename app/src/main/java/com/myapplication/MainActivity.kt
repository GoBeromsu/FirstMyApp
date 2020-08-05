package com.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapplication.Database.Plan
import com.myapplication.Database.PlanAdapter
import com.myapplication.Database.PlanDB
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var planDB: PlanDB? = null
    private var planList = listOf<Plan>()
    lateinit var mAdapter: PlanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        planDB = PlanDB.getInstance(this)
        mAdapter = PlanAdapter(this, planList)


        val r = Runnable {
            try {
                planList = planDB?.planDao()?.getAll()!!
                mAdapter = PlanAdapter(this, planList)
                mAdapter.notifyDataSetChanged()

                mRecyclerView.adapter = mAdapter
                mRecyclerView.layoutManager = LinearLayoutManager(this)
                mRecyclerView.setHasFixedSize(true)

            } catch (e: Exception) {
                Log.d("tag", "Error - $e")
            }
        }

        val thread = Thread(r)
        thread.start()

        mAddBtn.setOnClickListener {
            val i = Intent(this, AddActivity::class.java)
            startActivity(i)
            finish()
        }
    }


}


