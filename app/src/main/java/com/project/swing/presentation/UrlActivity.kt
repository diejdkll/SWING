package com.project.swing.presentation

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.project.feed.viewmodel.FeedViewModel
import com.project.feed.viewmodel.SetAccessTokenUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UrlActivity : ComponentActivity() {
    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intentData(intent.data)
        observeAccessTokenState()

        setContent { 
            Box(modifier = Modifier.fillMaxSize())
        }
    }

    private fun intentData(uri: Uri?) {
        uri?.let {
            val code = it.getQueryParameter("code")
            if (!code.isNullOrBlank()) {
                viewModel.requestAccessToken(code)
            } else {
                Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeAccessTokenState() {
        lifecycleScope.launch {
            viewModel.setAccessTokenUiState.collect { uiState ->
                if (uiState is SetAccessTokenUiState.Success) {
                    finish()
                }
            }
        }
    }
}