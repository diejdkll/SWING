package com.project.bookmark

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.bookmark.component.BookmarkImageList
import com.project.bookmark.viewmodel.BookmarkViewModel
import com.project.bookmark.viewmodel.GetLikePhotosUiState
import com.project.bookmark.viewmodel.UnlikePhotoUiState

@Composable
fun BookmarkScreen(
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val getLikePhotosUiState by viewModel.getLikePhotosUiState.collectAsStateWithLifecycle()
    val unlikePhotoUiState by viewModel.unlikePhotoUiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    var currentPage by remember { mutableIntStateOf(1) }
    var isLoading by remember { mutableStateOf(false) }
    var listIndex by remember { mutableIntStateOf(1) }

    val lastVisibleItem = remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
    }

    LaunchedEffect("Get Like Photos") {
        viewModel.getLikePhotos(page = currentPage)
    }

    LaunchedEffect(lastVisibleItem.value) {
        if (getLikePhotosUiState is GetLikePhotosUiState.Success) {
            if (lastVisibleItem.value == ((getLikePhotosUiState as GetLikePhotosUiState.Success).data.lastIndex * currentPage) + (currentPage - 1)
                && currentPage < (getLikePhotosUiState as GetLikePhotosUiState.Success).data.size / 30) {
                viewModel.getLikePhotos(++currentPage)
            }
        }
    }

    LaunchedEffect(getLikePhotosUiState) {
        when (getLikePhotosUiState) {
            is GetLikePhotosUiState.Loading -> {
                isLoading = true
            }
            is GetLikePhotosUiState.Success -> {
                val data = (getLikePhotosUiState as GetLikePhotosUiState.Success).data

                viewModel.addPhotos(data)
                isLoading = false
            }
            is GetLikePhotosUiState.Error -> {
                Toast.makeText(context, (getLikePhotosUiState as GetLikePhotosUiState.Error).exception.message.toString(), Toast.LENGTH_SHORT).show()
                isLoading = false
            }
            is GetLikePhotosUiState.ResultEmpty -> {
                Toast.makeText(context, "결과가 없습니다", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
        }
    }

    LaunchedEffect(unlikePhotoUiState) {
        if (unlikePhotoUiState is UnlikePhotoUiState.Success) {
            viewModel.photos.removeAt(listIndex)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BookmarkImageList(
            listState = listState,
            isLoading = isLoading,
            photos = viewModel.photos
        ) { id, index ->
            listIndex = index
            viewModel.unlikePhoto(id)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkScreenPreView() {
    BookmarkScreen()
}