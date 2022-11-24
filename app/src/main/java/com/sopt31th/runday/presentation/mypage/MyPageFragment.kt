package com.sopt31th.runday.presentation.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sopt31th.runday.databinding.FragmentMyPageBinding

class MyPageFragment : Fragment() {
    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel by viewModels<MyPageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyPageBinding.inflate(layoutInflater,container,false)

        viewModel.mypageResult.observe(viewLifecycleOwner){
            binding.tvNameMypage.text = it.data.name
            binding.tvTimeMypage.text = it.data.time
            binding.tvDistanceMypage.text = it.data.distance
            binding.tvPaceMypage.text= it.data.pace
            binding.tvKcalMypage.text = it.data.calorie
            binding.tvTimeMypage.text = it.data.time
        }
        binding.btnEditMypage.setOnClickListener{
            Log.d("sss","sss")
            viewModel.getUser()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}