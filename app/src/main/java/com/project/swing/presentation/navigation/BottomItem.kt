package com.project.swing.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

const val FEED = "feed"
const val BOOKMARK = "bookmark"

sealed class BottomItem(val title: String, val icon: ImageVector, val route: String) {
    data object Feed : BottomItem("피드", Icons.Default.Home, FEED)
    data object Bookmark : BottomItem("즐겨찾기", Icons.Default.Favorite, BOOKMARK)
}