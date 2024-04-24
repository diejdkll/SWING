package com.project.swing.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.project.swing.BuildConfig
import com.project.swing.presentation.navigation.SwingNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            SwingNavHost(navController = navController)

            oauth()
        }
    }

    private fun oauth() {
        val url =
            "https://unsplash.com/oauth/authorize?client_id=${BuildConfig.ACCESS_KEY}&redirect_uri=${BuildConfig.REDIRECT_URL}&response_type=code&scope=public+write_likes"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}