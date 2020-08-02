package com.example.todo.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todo.R
import com.example.todo.data.Project
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() , AddProjectDialog.NoticeDialogListener {


    private lateinit var viewModel: MainViewModel
    private lateinit var factory: MainViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        factory = MainViewModelFactory(requireActivity())
        viewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = ProjectAdapter()
        project_recyclreview.also {
            it.adapter = adapter
            it.layoutManager = GridLayoutManager(requireContext(),2)
        }
        viewModel.projects.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        floatingActionButton.setOnClickListener {
            addProjectDialog()
        }


    }

    private fun addProjectDialog() {
        val dialog = AddProjectDialog()
        dialog.show(f,"addProjectFragment")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, project: Project) {
        TODO("Not yet implemented")
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        TODO("Not yet implemented")
    }


}
