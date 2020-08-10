package com.myapplication.Database

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.DeleteBtnListener
import com.myapplication.R
import kotlinx.android.synthetic.main.listview.view.*
import java.util.*


class PlanAdapter(val context: Context, deletelistener: DeleteBtnListener) :
    RecyclerView.Adapter<PlanAdapter.Holder>() {
    private var DeleteBtnListener: DeleteBtnListener = deletelistener
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
        holder.bind(current)

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
        val delete = itemView.bt_delete

        val alarm: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val rington : Ringtone = RingtoneManager.getRingtone(context, alarm)

        fun bind(plan: Plan) {
            content.text = plan.content

            timeProgress.setOnTouchListener(OnTouchListener { v, event -> true })

            delete.setOnClickListener(View.OnClickListener {
                DeleteBtnListener.deleteBtnClicked(plan)
                notifyDataSetChanged()
            })

            start.setOnClickListener {
                Log.d("Tag", "start button")
                if (timeProgress.currentValue != 0f) {
                    Log.d("Tag", "progress. start button click listener")
                } else {
                    timeProgress.setValueAnimated(100f, plans[position].time.toLong() * 1000)

                }
            }

            reset.setOnClickListener {
                Log.d("Tag", "init button")
                timeProgress.setValue(0f)
            }

        }
    }
}