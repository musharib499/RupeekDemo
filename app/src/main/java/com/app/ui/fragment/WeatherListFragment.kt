package com.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.data.api.LoadingState
import com.app.data.api.Resource
import com.app.ui.MainActivity
import com.app.ui.base.BaseAdapterBinding
import com.app.ui.viewModule.WeatherListViewModel
import com.app.R
import com.app.data.model.WeatherResponse
import com.app.databinding.BookListFragmentBinding
import com.app.databinding.ItemWeatherListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherListFragment : Fragment(), BaseAdapterBinding.BindAdapterListener {

    companion object {
        fun newInstance() = WeatherListFragment()
    }
    private val viewModel: WeatherListViewModel by viewModels()
    private lateinit var binding: BookListFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = BookListFragmentBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@WeatherListFragment
            vm = viewModel
            adapter = BaseAdapterBinding<WeatherResponse.Data>(this@WeatherListFragment.requireContext(), null, this@WeatherListFragment, R.layout.item_weather_list)

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        (activity as MainActivity).supportActionBar?.title = "Weather List"
        viewModel.load(viewLifecycleOwner)

    }

    override fun onBind(holder: BaseAdapterBinding.DataBindingViewHolder, position: Int) {
        if (holder.binding is ItemWeatherListBinding) {
           holder.binding.item = binding.adapter?.item(position) as WeatherResponse.Data
        }
    }

}


