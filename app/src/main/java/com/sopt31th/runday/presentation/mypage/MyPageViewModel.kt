package com.sopt31th.runday.presentation.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt31th.runday.data.ahra.ApiClient
import com.sopt31th.runday.data.ahra.MyPageService
import com.sopt31th.runday.data.ahra.ResponseMyPageDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel : ViewModel(){
    private var _mypageResult : MutableLiveData<ResponseMyPageDTO> = MutableLiveData()
    val mypageResult : LiveData<ResponseMyPageDTO>
        get() = _mypageResult
    private val mypageService = ApiClient.getRetrofit().create(MyPageService::class.java)

    fun getUser() {
        mypageService.getUser(1)
            .enqueue(object : Callback<ResponseMyPageDTO>{
                override fun onResponse(
                    call: Call<ResponseMyPageDTO>,
                    response: Response<ResponseMyPageDTO>
                ) {
                    if (response.isSuccessful){
                        Log.d("서버통신완!",response.message())
                        _mypageResult.value = response.body()
                    }else{
                        Log.d("제발 떠줘!",response.message())
                    }
                }

                override fun onFailure(call: Call<ResponseMyPageDTO>, t: Throwable) {
                    Log.d("서버오류",t.toString())
                }

            })
    }
}