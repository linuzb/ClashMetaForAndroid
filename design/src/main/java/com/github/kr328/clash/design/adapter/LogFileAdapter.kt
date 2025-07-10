package com.android.system.update.design.adapter

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.android.system.update.design.model.LogFile
import com.android.system.update.design.util.format
import com.android.system.update.design.view.ActionLabel

class LogFileAdapter(
    private val context: Context,
    private val open: (LogFile) -> Unit,
) : RecyclerView.Adapter<LogFileAdapter.Holder>() {
    class Holder(val label: ActionLabel) : RecyclerView.ViewHolder(label)

    var logs: List<LogFile> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(ActionLabel(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        })
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val current = logs[position]

        holder.label.text = current.fileName
        holder.label.subtext = current.date.format(context)
        holder.label.setOnClickListener {
            open(current)
        }
    }

    override fun getItemCount(): Int {
        return logs.size
    }
}