package com.example.todo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.data.Project
import com.example.todo.databinding.ProjectListItemBinding

class ProjectAdapter : ListAdapter<Project, ProjectAdapter.ProjectViewHolder>(ProjectDiffCallbcks()) {
    inner class ProjectViewHolder(private val binding : ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project){
            binding.project = project

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(ProjectListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

class ProjectDiffCallbcks : DiffUtil.ItemCallback<Project>(){
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.id == newItem.id
    }

}
