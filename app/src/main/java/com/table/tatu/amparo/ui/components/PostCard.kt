package com.table.tatu.amparo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.table.tatu.amparo.R
import com.table.tatu.amparo.models.Post

@Composable
fun PostCard(post: Post){
    Surface(
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            Modifier
                .heightIn(220.dp, 240.dp)
                .width(367.dp)
        ) {
            val painter = rememberAsyncImagePainter(
                model = post.imageUrl,
                placeholder = painterResource(id = R.drawable.ic_amparo_launcher))

            Image(
                painter = painter,
                contentDescription = post.title,
                modifier = Modifier
                    .width(367.dp)
                    .height(175.3.dp))
            Text(
                text = post.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 11.11.dp))
        }
    }
}

@Preview
@Composable
private fun PostCardPreview(){
    PostCard(
        Post(
            id = "fjkdfjf9d",
            title = "Não se cale, Denuncie !",
            creationDate = "11/12/2024",
            imageUrl = "",
            content = listOf()
        )
    )
}