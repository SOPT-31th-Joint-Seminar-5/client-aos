package com.sopt31th.runday.presentation.yongmin.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sopt31th.runday.R
import com.sopt31th.runday.databinding.FragmentTimeExerciseBinding
import com.sopt31th.runday.presentation.running.RunningActivity
import com.sopt31th.runday.presentation.yongmin.home.adapter.ExerciseAdapter
import com.sopt31th.runday.presentation.yongmin.home.adapter.ExerciseItemDecorator
import com.sopt31th.runday.presentation.yongmin.home.viewmodel.TimeExerciseViewModel

class TimeExerciseFragment : Fragment() {
    private var _binding: FragmentTimeExerciseBinding? = null
    private val binding get() = requireNotNull(_binding) { "${this::class.java.simpleName}에서 바인딩 초기화 에러가 발생했습니다." }
    private lateinit var adapter: ExerciseAdapter
    private val timeViewModel: TimeExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimeExerciseBinding.inflate(layoutInflater)
        initAdapter()
        initNetwork()
        observeTimeExerciseInfo()
        return binding.root
    }

    private fun initAdapter() {
        adapter = ExerciseAdapter(
            { clickLiking(it) },
            { clickLikingDelete(it) },
            { imageView, exerciseId -> observeLikingSuccess(imageView, exerciseId) },
            { goRunningActivity() }
        )
        binding.rvTimeExercise.adapter = adapter
        binding.rvTimeExercise.addItemDecoration(ExerciseItemDecorator())
    }

    private fun goRunningActivity() {
        val intent = Intent(requireContext(), RunningActivity::class.java)
        startActivity(intent)
    }

    private fun clickLiking(exerciseId: Int) {
        timeViewModel.putTimeExerciseLike(exerciseId)
    }

    private fun clickLikingDelete(exerciseId: Int) {
        timeViewModel.deleteTimeExerciseLike(exerciseId)
    }

    private fun observeLikingSuccess(imageView: ImageView, exerciseId: Int) {
        timeViewModel.putIsLikedSucceed.observe(viewLifecycleOwner) {
            if (it.runId == exerciseId) {
                when (it.isLiked) {
                    true -> imageView.setImageResource(R.drawable.icon_heart_filled)
                    false -> imageView.setImageResource(R.drawable.icon_heart)
                }
            }
        }
    }

    private fun observeTimeExerciseInfo() {
        timeViewModel.timeExerciseInfo.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun initNetwork() {
        timeViewModel.getTimeExerciseInfo()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
