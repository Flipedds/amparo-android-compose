package com.table.tatu.amparo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.samples.samplePosts

@Composable
fun PostSection(
    posts: List<Post>
){
    LazyColumn(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 31.5.dp, vertical = 5.dp)
            .heightIn(258.dp, 590.dp),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        items(posts){ evento ->
            PostCard(post = evento)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun PostSectionPreview(){
    PostSection(
        posts = samplePosts
    )
}