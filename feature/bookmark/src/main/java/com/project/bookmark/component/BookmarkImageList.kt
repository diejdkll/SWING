package com.project.bookmark.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.model.UnsplashPhotoModel

@Composable
fun BookmarkImageList(
    listState: LazyListState,
    isLoading: Boolean,
    photos: List<UnsplashPhotoModel>,
    itemClick: (id: String, index: Int) -> Unit
) {
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(photos.size) {
            BookmarkImageListItem(
                itemData = photos[it]
            ) { id ->
                itemClick(id, it)
            }
        }
        if (isLoading) {
            item {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun BookmarkImageListItem(
    itemData: UnsplashPhotoModel,
    onItemClick: (id: String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = itemData.urls.regular,
            contentScale = ContentScale.Crop,
            contentDescription = "Bookmark Image"
        )
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = { onItemClick(itemData.id) }
        ) {
            if (itemData.likedByUser) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Bookmark Icon")
            } else {
                Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = "No Bookmark Icon")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkImageListPreView() {
    //BookmarkImageList()
}