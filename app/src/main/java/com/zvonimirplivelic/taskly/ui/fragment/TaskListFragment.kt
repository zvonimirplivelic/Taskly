package com.zvonimirplivelic.taskly.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zvonimirplivelic.taskly.R
import com.zvonimirplivelic.taskly.ui.TasklyViewModel
import com.zvonimirplivelic.taskly.ui.adapter.TaskListAdapter


class TaskListFragment : Fragment() {

    private lateinit var viewModel: TasklyViewModel
    private lateinit var fabAddTask: FloatingActionButton
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)

        val adapter = TaskListAdapter()

        recyclerView = view.findViewById(R.id.rv_task_list)
        fabAddTask = view.findViewById(R.id.fab_add_task)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(TasklyViewModel::class.java)
        viewModel.getAllTasks.observe(viewLifecycleOwner, { taskList ->
            adapter.setData(taskList)
        })


        fabAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        }

        return view
    }
}