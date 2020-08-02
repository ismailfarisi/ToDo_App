package com.example.todo.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.todo.R
import com.example.todo.data.Project
import kotlinx.android.synthetic.main.dialog_add_project.*
import java.lang.IllegalStateException
import java.util.zip.Inflater

class AddProjectDialog : DialogFragment() {

    internal lateinit var listener : NoticeDialogListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.dialog_add_project,null))
                .setPositiveButton("Add", DialogInterface.OnClickListener{ dialogInterface, i ->
                    val name = projectName_editText.text.toString()
                    val description = projectDescription_editText.text.toString()
                    val project = Project(projectName = name,projectDescription = description)
                    listener.onDialogPositiveClick(this,project)
                })
                .setNegativeButton("cncel",null)
            builder.create()
        }?: throw IllegalStateException("Activity cant be null")
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener =  context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() + " must implement NoticeDialogListener"))
        }
    }
    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment,project: Project)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }
}