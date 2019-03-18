package com.example.bobross

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.bobross.base.fragment.BaseFragment
import com.example.bobross.databinding.MainFragmentBinding
import com.example.bobross.di.ViewModelProviderFactory
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject lateinit var factory: ViewModelProviderFactory
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MainFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container,
            false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = factory.get(this, MainViewModel::class)
        viewModel.getPosts()
        viewModel.posts.observe(this, Observer {

        })
    }

}
