package com.zvonimirplivelic.taskly.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zvonimirplivelic.taskly.R
import com.zvonimirplivelic.taskly.db.model.Task
import com.zvonimirplivelic.taskly.ui.fragment.TaskListFragmentDirections

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private var taskList = emptyList<Task>()

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = taskList[position]
        var listItem = holder.itemView.findViewById<ConstraintLayout>(R.id.task_list_item)
        var tvTaskName = holder.itemView.findViewById<TextView>(R.id.tv_task_name)
        var tvTaskDetails = holder.itemView.findViewById<TextView>(R.id.tv_task_details)
        var tvTaskPriority = holder.itemView.findViewById<TextView>(R.id.tv_task_priority)

        tvTaskName.text = currentTask.taskName
        tvTaskDetails.text = currentTask.taskDetails
        tvTaskPriority.text = currentTask.taskPriority.toString()

        listItem.setOnClickListener {
            val action =
                TaskListFragmentDirections.actionTaskListFragmentToUpdateTaskFragment(currentTask)
            listItem.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = taskList.size

    fun setData(task: List<Task>) {
        this.taskList = task
        notifyDataSetChanged()
    }
}