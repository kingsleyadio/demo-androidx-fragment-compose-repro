package com.example.demo_androidx_fragment_compose_repro

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.core.view.get
import androidx.fragment.app.FragmentActivity
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import com.example.demo_androidx_fragment_compose_repro.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(R.layout.activity_main) {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.bind(findViewById<ViewGroup>(android.R.id.content)[0])
    }
    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        binding.container.getFragment<NavHostFragment>().navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        navController.apply {
            graph = createGraph("main") {
                fragment<MainFragment>("main")
                fragment<DetailFragment>("detail")
            }
        }
    }
}
