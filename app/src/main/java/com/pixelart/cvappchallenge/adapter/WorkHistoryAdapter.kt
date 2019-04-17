package com.pixelart.cvappchallenge.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pixelart.cvappchallenge.R
import com.pixelart.cvappchallenge.common.GlideApp
import com.pixelart.cvappchallenge.model.WorkHistory

class WorkHistoryAdapter: ListAdapter<WorkHistory, WorkHistoryAdapter.ViewHolder>(diffUtil) {
    private lateinit var context: Context
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.workhistory_rv_layout, parent, false)
        return ViewHolder(view, context)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workHistory = getItem(position)
        holder.setContent(workHistory)
    }
    
    class ViewHolder(view: View, private val context: Context): RecyclerView.ViewHolder(view) {
        private val companyName: TextView = view.findViewById(R.id.tvComapanyName)
        private val role: TextView = view.findViewById(R.id.tvRole)
        private val fromTo:TextView = view.findViewById(R.id.tvFromTo)
        private val appDescription: TextView = view.findViewById(R.id.tvAppDescription)
        private val responsibilities: TextView = view.findViewById(R.id.tvResponsibilities)
        private val icon: ImageView = view.findViewById(R.id.ivLogo)

        @SuppressLint("SetTextI18n")
        fun setContent(workHistory: WorkHistory){
            companyName.text = workHistory.companyName
            role.text = workHistory.role
            fromTo.text = "${workHistory.from} - ${workHistory.to}"
            appDescription.text = workHistory.appDescription
            responsibilities.text = workHistory.mainResponsibilities.joinToString("\n\u2022", "\u2022", "",-1, "")

            GlideApp.with(context)
                .load(workHistory.logo)
                .into(icon)
        }
    }
    
    companion object{
        val diffUtil: DiffUtil.ItemCallback<WorkHistory> = object: DiffUtil.ItemCallback<WorkHistory>(){
            override fun areItemsTheSame(oldItem: WorkHistory, newItem: WorkHistory): Boolean {
                return oldItem == newItem
            }
    
            override fun areContentsTheSame(oldItem: WorkHistory, newItem: WorkHistory): Boolean {
                return oldItem == newItem
            }
        }
    }
}