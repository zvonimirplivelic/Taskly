package com.zvonimirplivelic.taskly.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.NumberPicker
import androidx.navigation.fragment.navArgs
import com.zvonimirplivelic.taskly.R

class UpdateTaskFragment : Fragment() {

    private val args by navArgs<UpdateTaskFragmentArgs>()
    private lateinit var etUpdateTaskName: EditText
    private lateinit var etUpdateTaskDetails: EditText
    private lateinit var npUpdateTaskPriority: NumberPicker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_task, container, false)

        etUpdateTaskName = view.findViewById(R.id.et_update_task_name)
        etUpdateTaskDetails = view.findViewById(R.id.et_update_task_details)
        npUpdateTaskPriority = view.findViewById(R.id.np_update_priority)

        etUpdateTaskName.setText(args.currentTask.taskName)
        etUpdateTaskDetails.setText(args.currentTask.taskDetails)
        npUpdateTaskPriority.apply {
            minValue = 0
            maxValue = 10
            value = args.currentTask.taskPriority
        }

        return view
    }
}