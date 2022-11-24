package com.sopt31th.runday.presentation.yongmin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sopt31th.runday.databinding.FragmentLevelExerciseBinding

class LevelExerciseFragment : Fragment() {
    private var _binding: FragmentLevelExerciseBinding? = null
    private val binding get() = requireNotNull(_binding) { "${this::class.java.simpleName}에서 바인딩 초기화 에러가 발생했습니다." }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLevelExerciseBinding.inflate(layoutInflater)
        return binding.root
    }
}
