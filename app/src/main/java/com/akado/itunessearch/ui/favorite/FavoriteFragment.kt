package com.akado.itunessearch.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akado.itunessearch.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel by viewModels<FavoriteViewModel>()
    private var _binding: FragmentFavoriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.let {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.request()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}