package com.table.tatu.amparo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.table.tatu.amparo.R
import com.table.tatu.amparo.models.Post
import com.table.tatu.amparo.ui.components.PostSection
import com.table.tatu.amparo.ui.theme.amparoDefaultColor
import com.table.tatu.amparo.ui.theme.amparoHomeSecondaryTextColor
import com.table.tatu.amparo.ui.theme.amparoHomeTextColor
import com.table.tatu.amparo.ui.theme.grandstanderFontFamily
import com.table.tatu.amparo.ui.viewmodels.PostScreenViewModel
import com.table.tatu.amparo.ui.viewmodels.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostsScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .background(amparoDefaultColor)
    ) {
        Image(
            painterResource(id = R.drawable.ic_amparo_launcher),
            contentDescription = "Logo Amparo",
            modifier = Modifier
                .width(302.dp)
                .height(114.dp)
                .offset(x = 0.dp, y = -(10).dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Estamos aqui para lhe ajudar",
            fontFamily = grandstanderFontFamily,
            fontSize = 16.sp,
            color = amparoHomeTextColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .offset(y = -(25).dp)
        )
        Text(
            text = "peça ajuda",
            fontFamily = grandstanderFontFamily,
            fontSize = 24.sp,
            color = amparoHomeTextColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .offset(y = -(20).dp)
        )
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "busque ",
                fontFamily = grandstanderFontFamily,
                fontSize = 24.sp,
                color = amparoHomeTextColor,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .offset(y = -(20).dp)
            )
            Text(
                text = "Amparo",
                fontFamily = grandstanderFontFamily,
                fontSize = 24.sp,
                color = amparoHomeSecondaryTextColor,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .offset(y = -(20).dp)
            )
        }
        val viewModel = koinViewModel<PostScreenViewModel>()
        val postsState by viewModel.postsState.collectAsState()

        when (postsState) {
            is UiState.Loading -> Text(
                modifier = Modifier.padding(horizontal = 31.5.dp),
                text = "Carregando ...."
            )

            is UiState.Success -> PostSection(posts = (postsState as UiState.Success<List<Post>>).data)
            is UiState.Error -> Text(
                modifier = Modifier.padding(horizontal = 31.5.dp),
                text = (postsState as UiState.Error).message
            )
        }

    }
}


@Preview
@Composable
fun PostsScreenPreview(){
    PostsScreen()
}