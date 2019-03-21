package com.example.bobross

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.bobross.adapter.PostAdapter
import com.example.bobross.base.fragment.BaseFragment
import com.example.bobross.databinding.MainFragmentBinding
import com.example.bobross.di.ViewModelProviderFactory
import com.example.bobross.repository.model.Command
import com.example.bobross.repository.model.NoteCommand
import com.example.bobross.repository.model.Post
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var dataset: MutableList<Post>
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container,
            false)
        dataset = mutableListOf()
        viewAdapter = PostAdapter(dataset) { post, position ->
            viewModel.favorite(post, position)
        }
        binding.recyclerView.adapter = viewAdapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                    binding.fab.hide()
                else if (dy < 0)
                    binding.fab.show()
            }
        })
        binding.fab.setOnClickListener { viewModel.getNote() }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = factory.get(this, MainViewModel::class)
        viewModel.getPosts()
        viewModel.posts.observe(this, Observer {
            when (it) {
                is Command.Success -> {
                    progress.visibility = View.GONE
                    error.visibility = View.GONE
                    dataset.addAll(it.posts)
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                }
                is Command.Loading -> {
                    progress.visibility = View.VISIBLE
                }
                is Command.Error -> {
                    progress.visibility = View.GONE
                    error.visibility = View.VISIBLE
                    error.text = "Something went wrong"
                    // TODO handle errors properly
                }
            }
        })

        viewModel.notes.observe(this, Observer {
            when (it) {
                is NoteCommand.Loading -> progress.visibility = View.VISIBLE
                is NoteCommand.Success -> {
                    progress.visibility = View.GONE
                    Snackbar.make(view!!, it.result, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        viewModel.favorite.observe(this, Observer {
            if (it.updatedPost != null) {
                viewAdapter.notifyItemChanged(it.position!!)
            } else {
                // show error snackbar
            }
        })
    }
}
