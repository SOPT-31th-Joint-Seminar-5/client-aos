package com.sopt31th.runday.presentation.yongmin.home.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt31th.runday.R
import com.sopt31th.runday.databinding.ItemTimeExerciseBinding
import com.sopt31th.runday.presentation.yongmin.home.TimeExerciseData

class ExerciseAdapter(
    private val clickLiking: (id: Int) -> Unit,
    private val clickLikingDelete: (id: Int) -> Unit,
    private val observeLikeSuccess: (imageView: ImageView, exerciseId: Int) -> Unit,
    private val goRunningActivity: () -> Unit
) :
    ListAdapter<TimeExerciseData, ExerciseAdapter.ExerciseViewHolder>(ExerciseComparator()) {

    class ExerciseViewHolder(
        private val binding: ItemTimeExerciseBinding,
        private val clickLiking: (id: Int) -> Unit,
        private val clickLikingDelete: (id: Int) -> Unit,
        private val observeLikeSuccess: (imageView: ImageView, exerciseId: Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TimeExerciseData) {
            binding.tvTitle.text = data.title
            binding.tvRoutine.text = data.routine
            binding.tvDifficulty.text = data.stage
            binding.imageView.setImageResource(data.image)
            changeTextColor(data.title, data.highlight)
            setHeartColor(data.isLiked)

            binding.ivHeart.setOnClickListener {
                when (binding.ivHeart.isSelected) {
                    true -> clickLikingDelete(data.id)
                    false -> clickLiking(data.id)
                }
                binding.ivHeart.isSelected = !binding.ivHeart.isSelected
            }
            observeLikeSuccess(binding.ivHeart, data.id)
        }

        private fun setHeartColor(isLiked: Boolean) {
            when (isLiked) {
                true -> {
                    binding.ivHeart.setImageResource(R.drawable.icon_heart_filled)
                    binding.ivHeart.isSelected = true
                }
                false -> {
                    binding.ivHeart.setImageResource(R.drawable.icon_heart)
                    binding.ivHeart.isSelected = false
                }
            }
        }

        private fun changeTextColor(title: String, word: String) {
            val startIndex = title.indexOf(word)
            val result = SpannableString(title)
            result.setSpan(
                ForegroundColorSpan(Color.BLUE),
                startIndex,
                startIndex + word.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.tvTitle.text = result
        }
    }

    class ExerciseComparator() : DiffUtil.ItemCallback<TimeExerciseData>() {
        override fun areItemsTheSame(
            oldItem: TimeExerciseData,
            newItem: TimeExerciseData
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TimeExerciseData,
            newItem: TimeExerciseData
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTimeExerciseBinding.inflate(layoutInflater)
        return ExerciseViewHolder(binding, clickLiking, clickLikingDelete, observeLikeSuccess)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.itemView.setOnClickListener { goRunningActivity() }
        holder.onBind(getItem(position))
    }
}
