package com.ny.regularnav

import android.media.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.juying.reader.ui.main.my.My
import com.ny.regularnav.ui.theme.bookshelfPage.BookShelf
import com.ny.regularnav.ui.theme.discover.Discover
import kotlinx.coroutines.launch

enum class NavigationPagers(
    val icon: ImageVector
) {
    Bookshelf(Icons.Filled.Favorite),
    Discover( Icons.Filled.Favorite),
    My(Icons.Filled.Favorite)

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val pagerState = rememberPagerState(
        pageCount = { NavigationPagers.values().size }
    )

    Scaffold(
        bottomBar = {
            NavigationBar(pagerState)
        }
    ) {
        HorizontalPager(
            pagerState,
            modifier = Modifier.padding(it),
        ) { index ->
            when (index) {
                0 -> BookShelf()
                1 -> Discover()
                2 -> My()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavigationBar(
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    androidx.compose.material3.NavigationBar {
        NavigationPagers.values().forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.name) },
                label = { Text(item.name) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}