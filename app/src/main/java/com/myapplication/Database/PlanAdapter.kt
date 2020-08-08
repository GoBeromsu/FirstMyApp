package com.myapplication.Database

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.ButtonClickListener
import com.myapplication.R
import kotlinx.android.synthetic.main.listview.view.*


class PlanAdapter(val context: Context,listener:ButtonClickListener) :
    RecyclerView.Adapter<PlanAdapter.Holder>() {
    private var ButtonClickListener: ButtonClickListener = listener
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


    }

    internal fun setPlans(plans: List<Plan>) {
        this.plans = plans
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val content = itemView.tv_context
        val timeProgress = itemView.progress
        val start = itemView.bt_start
        val reset = itemView.bt_reset
        val modify = itemView.bt_delete

        fun bind(plan: Plan) {
            content.text = plan.content

            timeProgress.setOnTouchListener(OnTouchListener { v, event -> true })
            modify.setOnClickListener(View.OnClickListener { v: View? ->
                ButtonClickListener.deleteBtnClicked(plan)
                notifyDataSetChanged()
            })
        }
    }
}