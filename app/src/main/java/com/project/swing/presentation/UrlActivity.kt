package com.project.swing.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.project.swing.presentation.feed.viewmodel.FeedViewModel
import com.project.swing.presentation.feed.viewmodel.SetAccessTokenUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UrlActivity : ComponentActivity() {
    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                viewModel.requestAccessToken(code)
            }
        } else {
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launch {
            viewModel.setAccessTokenUiState
                .collect {
                    if (it is SetAccessTokenUiState.Success) {
                        finish()
                    }
                }
        }
        
        setContent { 
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}