package com.sopt31th.runday.presentation.yongmin.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt31th.runday.R
import com.sopt31th.runday.data.RundayService
import com.sopt31th.runday.data.ServiceFactory
import com.sopt31th.runday.data.yongmin.RequestLikeDto
import com.sopt31th.runday.data.yongmin.ResponseExerciseDto
import com.sopt31th.runday.data.yongmin.ResponseLikeDto
import com.sopt31th.runday.presentation.yongmin.home.TimeExerciseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class TimeExerciseViewModel : ViewModel() {
    private val _timeExerciseInfo = MutableLiveData<List<TimeExerciseData>>()
    val timeExerciseInfo: LiveData<List<TimeExerciseData>> get() = _timeExerciseInfo

    private val _putIsLikedSucceed = MutableLiveData<ResponseLikeDto.Data>()
    val putIsLikedSucceed: LiveData<ResponseLikeDto.Data> get() = _putIsLikedSucceed

    fun deleteTimeExerciseLike(isLikedId: Int) {
        val rundayService = ServiceFactory.rundayService.create(RundayService::class.java)
        rundayService.deleteExerciseLike(isLikedId, RequestLikeDto(isLikedId, 1))
            .enqueue(object : Callback<ResponseLikeDto> {
                override fun onResponse(
                    call: Call<ResponseLikeDto>,
                    response: Response<ResponseLikeDto>
                ) {
                    if (response.isSuccessful) {
                        _putIsLikedSucceed.value = response.body()?.data
                    } else {
                        Log.e("home server connect", "onResponse Error")
                    }
                }

                override fun onFailure(call: Call<ResponseLikeDto>, t: Throwable) {
                    Log.e("home server connect", "onFailure, ${t.message}")
                }
            })
    }

    fun putTimeExerciseLike(isLikedId: Int) {
        val rundayService = ServiceFactory.rundayService.create(RundayService::class.java)
        rundayService.putExerciseLike(isLikedId, RequestLikeDto(isLikedId, 1))
            .enqueue(object : Callback<ResponseLikeDto> {
                override fun onResponse(
                    call: Call<ResponseLikeDto>,
                    response: Response<ResponseLikeDto>
                ) {
                    if (response.isSuccessful) {
                        _putIsLikedSucceed.value = response.body()?.data
                    } else {
                        Log.e("home server connect", "onResponse Error")
                    }
                }

                override fun onFailure(call: Call<ResponseLikeDto>, t: Throwable) {
                    Log.e("home server connect", "onFailure, ${t.message}")
                }
            })
    }

    fun getTimeExerciseInfo() {
        val rundayService = ServiceFactory.rundayService.create(RundayService::class.java)
        rundayService.getTimeExercise(1).enqueue(object : Callback<ResponseExerciseDto> {
            override fun onResponse(
                call: Call<ResponseExerciseDto>,
                response: Response<ResponseExerciseDto>
            ) {
                if (response.isSuccessful) {
                    _timeExerciseInfo.value = mapTimeExercise(
                        response.body()?.data ?: throw IllegalArgumentException("빈 리스트가 날아왔습니다.")
                    )
                } else {
                    Log.e("home server connect", "onResponse Error")
                }
            }

            override fun onFailure(call: Call<ResponseExerciseDto>, t: Throwable) {
                Log.e("home server connect", "onFailure, ${t.message}")
            }
        })
    }

    private fun mapTimeExercise(response: List<ResponseExerciseDto.Data>): List<TimeExerciseData> {
        val images = listOf(
            R.drawable.time_image_1,
            R.drawable.time_image_2,
            R.drawable.time_image_3,
            R.drawable.time_image_4,
            R.drawable.time_image_5,
            R.drawable.time_image_6,
            R.drawable.time_image_7
        )
        val result = response.map {
            TimeExerciseData(
                id = it.id,
                highlight = it.highlight,
                isLiked = it.isLiked,
                routine = it.routine,
                stage = it.stage,
                title = it.title,
                image = 0
            )
        }
        result.forEachIndexed { idx, data ->
            data.image = images[idx]
        }
        return result
    }
}
