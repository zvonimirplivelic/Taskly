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
import androidx.navigation.fragment.navArgs
import com.zvonimirplivelic.taskly.R
import com.zvonimirplivelic.taskly.db.model.Task
import com.zvonimirplivelic.taskly.ui.TasklyViewModel

class UpdateTaskFragment : Fragment() {

    private val args by navArgs<UpdateTaskFragmentArgs>()

    private lateinit var viewModel: TasklyViewModel

    private lateinit var etUpdateTaskName: EditText
    private lateinit var etUpdateTaskDetails: EditText
    private lateinit var npUpdateTaskPriority: NumberPicker
    private lateinit var btnUpdateTask: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_task, container, false)

        viewModel = ViewModelProvider(this).get(TasklyViewModel::class.java)

        etUpdateTaskName = view.findViewById(R.id.et_update_task_name)
        etUpdateTaskDetails = view.findViewById(R.id.et_update_task_details)
        npUpdateTaskPriority = view.findViewById(R.id.np_update_priority)
        btnUpdateTask = view.findViewById(R.id.btn_update_task)

        etUpdateTaskName.setText(args.currentTask.taskName)
        etUpdateTaskDetails.setText(args.currentTask.taskDetails)
        npUpdateTaskPriority.apply {
            minValue = 0
            maxValue = 10
            value = args.currentTask.taskPriority
        }

        btnUpdateTask.setOnClickListener {
            updateTask()
        }

        return view
    }

    private fun updateTask() {
        val taskName = etUpdateTaskName.text.toString()
        val taskDetails = etUpdateTaskDetails.text.toString()
        val taskPriority = npUpdateTaskPriority.value

        if (validateUserInput(taskName, taskDetails)) {
            val updatedTask = Task(args.currentTask.taskId, taskName, taskDetails, taskPriority)
            viewModel.updateTask(updatedTask)
            Toast.makeText(requireContext(), "Successfully updated task", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateTaskFragment_to_taskListFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out the fields!", Toast.LENGTH_LONG).show()
        }
    }


    private fun validateUserInput(taskName: String, taskDetails: String): Boolean {
        return !(TextUtils.isEmpty(taskName) || TextUtils.isEmpty(taskDetails))
    }
}