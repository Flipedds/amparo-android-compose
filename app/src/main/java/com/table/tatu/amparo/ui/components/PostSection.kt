package com.table.tatu.amparo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.samples.samplePosts
import com.table.tatu.amparo.ui.viewmodels.PostScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostSection(
    posts: List<Post>,
    viewModel: PostScreenViewModel
){
    var isRefreshing by remember { mutableStateOf(false) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.refreshPosts()
        }
    )
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
    ) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
                .zIndex(1f)
        )
        LazyColumn(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 31.5.dp, vertical = 5.dp)
                .heightIn(258.dp, 590.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            items(posts) { evento ->
                PostCard(post = evento)
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PostSectionPreview(){
    PostSection(
        posts = samplePosts,
        viewModel = koinViewModel()
    )
}