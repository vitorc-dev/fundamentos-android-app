package com.example.fundamentosandroidapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.fundamentosandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val  navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcvMainActivity) as? NavHostFragment
        navHostFragment?.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonNextFragment.setOnClickListener {
            navController?.currentDestination?.id.let {
                when(it){
                    R.id.firstFragment -> {
                        navController?.navigate(R.id.action_firstFragment_to_secondFragment)

                        binding.buttonNextFragment.text =
                            getString(R.string.go_back_to_the_first_fragment)
                    }
                    R.id.secondFragment -> {
                        navController?.popBackStack()

                        binding.buttonNextFragment.text =
                            getString(R.string.go_to_the_next_page)
                    }
                }
            }


        }
    }
}