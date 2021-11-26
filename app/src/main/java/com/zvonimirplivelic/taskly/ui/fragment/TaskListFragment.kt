package com.zvonimirplivelic.taskly.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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
        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.taskly_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setTitle("Delete all tasks?")
            setMessage("Do you want to delete all tasks?")
            setPositiveButton("Yes") { _, _ ->

                viewModel.deleteAllTasks()

                Toast.makeText(
                    requireContext(),
                    "Successfully deleted all tasks",
                    Toast.LENGTH_LONG
                ).show()

                findNavController().navigate(R.id.action_updateTaskFragment_to_taskListFragment)
            }
            setNegativeButton("No") { _, _ ->

            }
            create().show()
        }
    }
}