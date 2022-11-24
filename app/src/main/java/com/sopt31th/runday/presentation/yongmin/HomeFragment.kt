package com.sopt31th.runday.presentation.yongmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.sopt31th.runday.R
import com.sopt31th.runday.databinding.FragmentHomeBinding
import com.sopt31th.runday.presentation.yongmin.home.TimeExerciseFragment

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { "${this::class.java.simpleName}에서 바인딩 초기화 에러가 발생했습니다." }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(2))
        initTabSelectListener()
        return binding.root
    }

    private fun initTabSelectListener() {
        val transaction = childFragmentManager.beginTransaction()
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {}
                    1 -> {}
                    2 -> transaction.replace(R.id.fcv_exercise, TimeExerciseFragment())
                    3 -> {}
                }
                transaction.replace(R.id.fcv_exercise, TimeExerciseFragment())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
