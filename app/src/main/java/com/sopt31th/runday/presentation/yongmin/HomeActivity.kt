package com.sopt31th.runday.presentation.yongmin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt31th.runday.R
import com.sopt31th.runday.databinding.ActivityHomeBinding
import com.sopt31th.runday.presentation.mypage.MyPageFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activateBottomNavigation()
    }

    private fun activateBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId){
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HomeFragment())
                        .commit()
                    true}
                R.id.menu_record -> {true}
                R.id.menu_crew -> {true}
                R.id.menu_challenge -> {true}
                R.id.menu_my_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, MyPageFragment())
                        .commit()
                    true
                }
                else -> {true}
            }
        }
    }
}
