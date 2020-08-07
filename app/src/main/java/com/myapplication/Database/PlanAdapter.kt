package com.myapplication.Database

import android.content.Context
import android.icu.util.UniversalTimeScale.toLong
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.MainActivity
import com.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listview.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.security.AccessController.getContext
import java.util.Calendar.getInstance

class PlanAdapter(val context: Context) :
    RecyclerView.Adapter<PlanAdapter.Holder>() {

    private var plans = emptyList<Plan>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.listview, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return plans.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val current = plans[position]
        holder.bind(plans[position])

        holder.start.setOnClickListener {
            Log.d("Tag", "start button")
            if (holder.timeProgress.currentValue != 0f) {
                Log.d("Tag", "progress. start button click listener")
            } else {
                holder.timeProgress.setValueAnimated(100f, current.time.toLong() * 1000)
            }
        }
        holder.reset.setOnClickListener {
            Log.d("Tag", "init button")
            holder.timeProgress.setValue(0f)
        }
        holder.modify.setOnClickListener {}

    }

    internal fun setPlans(plans:List<Plan>){
        this.plans=plans
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val content = itemView.tv_context
        val timeProgress = itemView.progress
        val start = itemView.bt_start
        val reset = itemView.bt_reset
        val modify = itemView.bt_modify


        fun bind(plan: Plan) {
            content.text = plan.content
        }
    }
}