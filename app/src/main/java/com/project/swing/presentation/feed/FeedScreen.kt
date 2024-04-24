package com.project.swing.presentation.feed

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.project.swing.presentation.feed.component.FeedImageList
import com.project.swing.presentation.feed.component.FeedSearchTextField
import com.project.swing.presentation.feed.viewmodel.FeedViewModel
import com.project.swing.presentation.feed.viewmodel.LikePhotoUiState
import com.project.swing.presentation.feed.viewmodel.SearchPhotosUiState
import com.project.swing.presentation.feed.viewmodel.UnlikePhotoUiState

@Composable
fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val searchPhotosUiState by viewModel.searchPhotosUiState.collectAsStateWithLifecycle()
    val likePhotoUiState by viewModel.likePhotoUiState.collectAsStateWithLifecycle()
    val unlikePhotoUiState by viewModel.unlikePhotoUiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()
    var searchText by remember { mutableStateOf("") }
    var currentPage by remember { mutableIntStateOf(1) }
    var isLoading by remember { mutableStateOf(false) }
    var listIndex by remember { mutableIntStateOf(1) }

    val lastVisibleItem = remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
        }
    }

    LaunchedEffect(searchText) {
        currentPage = 1
    }

    LaunchedEffect(lastVisibleItem.value) {
        if (searchPhotosUiState is SearchPhotosUiState.Success) {
            if (lastVisibleItem.value == ((searchPhotosUiState as SearchPhotosUiState.Success).data.results.lastIndex * currentPage) + (currentPage - 1)
                && currentPage < (searchPhotosUiState as SearchPhotosUiState.Success).data.totalPages) {
                viewModel.searchPhotos(searchText, ++currentPage)
            }
        }
    }

    LaunchedEffect(searchPhotosUiState) {
        when (searchPhotosUiState) {
            is SearchPhotosUiState.Loading -> {
                isLoading = true
            }
            is SearchPhotosUiState.Success -> {
                val data = (searchPhotosUiState as SearchPhotosUiState.Success).data

                viewModel.addPhotos(data.results)
                isLoading = false
            }
            is SearchPhotosUiState.Error -> {
                Toast.makeText(context, (searchPhotosUiState as SearchPhotosUiState.Error).exception.message.toString(), Toast.LENGTH_SHORT).show()
                isLoading = false
            }
            is SearchPhotosUiState.ResultEmpty -> {
                Toast.makeText(context, "검색 결과가 없습니다", Toast.LENGTH_SHORT).show()
                isLoading = false
            }
            is SearchPhotosUiState.QueryEmpty -> isLoading = false
        }
    }

    LaunchedEffect(likePhotoUiState, unlikePhotoUiState) {
        if (likePhotoUiState is LikePhotoUiState.Success) {
            viewModel.photos[listIndex] = (likePhotoUiState as LikePhotoUiState.Success).data.photo
        }

        if (unlikePhotoUiState is UnlikePhotoUiState.Success) {
            viewModel.photos[listIndex] = (unlikePhotoUiState as UnlikePhotoUiState.Success).data.photo
        }
    }

    DisposableEffect("Remove Photos") {
        onDispose { viewModel.clearPhotos() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FeedSearchTextField(
            setText = searchText,
            placeHolder = "사진 검색...",
            onValueChange = {
                searchText = it
            },
            onSearchTextChange = {
                viewModel.clearPhotos()
                viewModel.searchPhotos(query = it, page = currentPage)
            }
        )
        FeedImageList(
            listState = listState,
            isLoading = isLoading,
            photos = viewModel.photos
        ) { id, isLike, index ->
            listIndex = index
            if (isLike) viewModel.unlikePhoto(id)
            else viewModel.likePhoto(id)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedScreenPreView() {
    FeedScreen()
}