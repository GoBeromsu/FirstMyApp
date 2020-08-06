package com.myapplication.Database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import kotlinx.android.synthetic.main.listview.view.*

class PlanAdapter(val context: Context, val plans: List<Plan>) :
    RecyclerView.Adapter<PlanAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.listview, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return plans.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(plans[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val content = itemView.tv_context
        val time = itemView.tv_time
        val start = itemView.bt_start
        val reset = itemView.bt_reset
        val modify = itemView.bt_modify
        val delete = itemView.bt_delete


        fun bind(plan: Plan) {

            content.text = plan.content
            time.text = plan.time.toString()
        }
    }
}