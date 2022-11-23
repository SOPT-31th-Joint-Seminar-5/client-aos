package com.sopt31th.runday.presentation.running

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt31th.runday.R
import com.sopt31th.runday.databinding.ActivityRunningBinding
import java.util.*
import kotlin.concurrent.timer

class RunningActivity : AppCompatActivity() {
    lateinit var binding: ActivityRunningBinding
    private var _min = 5
    private var _sec = 0
    lateinit var sec : String
    lateinit var min : String
    private var progressPercent = 0
    private val pause = "pause"
    private val play = "play"
    lateinit var timerTask: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRunningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivPausePlay.setOnClickListener{
            when(binding.ivPausePlay.tooltipText){
                pause -> {
                    timerPause()
                }
                play -> {
                    timerPlay()
                }
            }
        }
        binding.progressBar.progress = progressPercent
    }

    private fun timerPlay() {
        timerTask = timer(period = 1000){
            if(_sec == 0){
                _min --
                _sec = 59
            }
            else _sec --
            sec = String.format("%02d", _sec)
            min = String.format("%02d", _min)
            progressPercent ++
            runOnUiThread {
                if(_min == 0 && _sec == 0){
                    timerPause()
                }
                binding.tvTimer.text = "$min:$sec"
                binding.progressBar.setProgress(progressPercent,true)
            }
        }
        binding.ivPausePlay.setImageResource(R.drawable.ic_pause_running)
        binding.ivPausePlay.tooltipText = pause
        binding.lottieVoice.resumeAnimation()
    }

    private fun timerPause() {
        binding.ivPausePlay.setImageResource(R.drawable.ic_play_running)
        binding.ivPausePlay.tooltipText = play
        timerTask.cancel()
        binding.lottieVoice.pauseAnimation()
    }
}