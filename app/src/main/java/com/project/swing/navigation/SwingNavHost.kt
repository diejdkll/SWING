package com.project.swing.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.project.bookmark.BookmarkScreen
import com.project.feed.FeedScreen

@Composable
fun SwingNavHost(navController: NavHostController) {
    Box {
        NavHost(
            navController = navController,
            startDestination = BottomItem.Feed.route,
            modifier = Modifier.padding(bottom = 56.dp)
        ) {
            composable(BottomItem.Feed.route) {
                FeedScreen()
            }
            composable(BottomItem.Bookmark.route) {
                BookmarkScreen()
            }
        }
        BottomNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )
    }
}