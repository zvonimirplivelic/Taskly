package com.zvonimirplivelic.taskly.ui.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.zvonimirplivelic.taskly.R
import com.zvonimirplivelic.taskly.db.model.Task
import com.zvonimirplivelic.taskly.ui.TasklyViewModel
import timber.log.Timber

class AddTaskFragment : Fragment() {

    private lateinit var viewModel: TasklyViewModel

    private lateinit var etTaskName: EditText
    private lateinit var etTaskDetails: EditText
    private lateinit var npPriority: NumberPicker
    private lateinit var addTaskButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        viewModel = ViewModelProvider(this)[TasklyViewModel::class.java]

        addTaskButton = view.findViewById(R.id.btn_add_task)
        etTaskName = view.findViewById(R.id.et_task_name)
        etTaskDetails = view.findViewById(R.id.et_task_details)
        npPriority = view.findViewById(R.id.np_priority)

        npPriority.apply {
            minValue = 0
            maxValue = 10
            value = 0
        }

        addTaskButton.setOnClickListener {
            addTaskToDatabase()
        }

        return view
    }

    private fun addTaskToDatabase() {
        val taskName = etTaskName.text.toString()
        val taskDetails = etTaskDetails.text.toString()
        val taskPriority = npPriority.value

        Timber.d("addTask: $taskName, $taskDetails, $taskPriority")

        if (validateUserInput(taskName, taskDetails)) {
            val task = Task(0, taskName, taskDetails, taskPriority)
            viewModel.addTask(task)
            Toast.makeText(requireContext(), "Task added!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_addTaskFragment_to_taskListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out the fields!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun validateUserInput(taskName: String, taskDetails: String): Boolean {
        return !(TextUtils.isEmpty(taskName) || TextUtils.isEmpty(taskDetails))
    }
}