package com.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.app.R
import com.app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var navigator: AppNavigatorInterface
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        if (savedInstanceState == null) {
            navigator?.navigator(Command.HOME)

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}