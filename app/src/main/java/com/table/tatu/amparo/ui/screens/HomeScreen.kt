package com.table.tatu.amparo.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.table.tatu.amparo.R
import com.table.tatu.amparo.ui.theme.amparoButtonColor
import com.table.tatu.amparo.ui.theme.amparoHomeTextColor
import com.table.tatu.amparo.ui.theme.amparoMenuColor
import com.table.tatu.amparo.ui.theme.grandstanderFontFamily

sealed class NavItem(
    @DrawableRes val icon: Int,
    val label: String
) {
    data object Home : NavItem(
        icon = R.drawable.ic_home,
        label = "Home"
    )

    data object PersonHelp : NavItem(
        icon = R.drawable.ic_account,
        label = "Perfil"
    )

    data object Denounce : NavItem(
        icon = R.drawable.ic_shield,
        label = "Denúncia"
    )

    data object CallHelp : NavItem(
        icon = R.drawable.ic_phone,
        label = "Ajuda"
    )

    data object LocalHelp : NavItem(
        icon = R.drawable.ic_map,
        label = "Locais"
    )
}

@Composable
fun HomeScreen(onLogout: () -> Unit) {
    val items = listOf(
        NavItem.Home,
        NavItem.PersonHelp,
        NavItem.Denounce,
        NavItem.CallHelp,
        NavItem.LocalHelp
    )

    var selectedItem by remember {
        mutableStateOf(items.first())
    }

    val pagerState = rememberPagerState {
        items.size
    }

    LaunchedEffect(selectedItem) {
        pagerState.animateScrollToPage(
            items.indexOf(selectedItem)
        )
    }

    LaunchedEffect(pagerState.targetPage) {
        selectedItem = items[pagerState.targetPage]
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(visible = showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            containerColor = amparoMenuColor,
            icon = {
                Image(
                    painterResource(id = R.drawable.ic_amparo_launcher),
                    contentDescription = "Logo Amparo",
                    modifier = Modifier
                        .width(302.dp)
                        .height(114.dp)
                )
            },
            text = {
                Text("Você tem certeza que deseja sair ?",
                    fontFamily = grandstanderFontFamily,
                    fontSize = 16.sp,
                    color = amparoHomeTextColor)
            },
            confirmButton = {
                TextButton(onClick =  { onLogout() }) {
                    Text("Sair",
                        fontFamily = grandstanderFontFamily,
                        fontSize = 16.sp,
                        color = amparoHomeTextColor)
                }
            },
            dismissButton = {
                TextButton(onClick =  { showDialog = false }) {
                    Text("Cancelar",
                        fontFamily = grandstanderFontFamily,
                        fontSize = 16.sp,
                        color = amparoHomeTextColor)
                }
            }
        )
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = amparoMenuColor) {
                items.forEach { navItem: NavItem ->
                    NavigationBarItem(
                        selected = navItem == selectedItem,
                        onClick = { selectedItem = navItem },
                        label = { Text(text = navItem.label)},
                        icon = {
                            Icon(
                                painter = painterResource(
                                    id = navItem.icon
                                ),
                                contentDescription = navItem.label
                            )
                        }
                    )
                }
            }
        },
        topBar = {
            Spacer(
                modifier = Modifier
                    .height(39.dp)
                    .fillMaxWidth()
                    .background(amparoButtonColor)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = amparoButtonColor,
                onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Sair")
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            pagerState,
            Modifier.padding(innerPadding)
        ) { page ->
            when (val item = items[page]) {
                NavItem.Home -> PostsScreen()
                NavItem.PersonHelp -> MockScreen(text = item.label)
                NavItem.LocalHelp -> AmparoScreen()
                NavItem.CallHelp -> AjudaScreen()
                NavItem.Denounce -> DenounceScreen()
            }
        }
    }
}

@Composable
fun MockScreen(text: String) {
    Box(Modifier.fillMaxSize()) {
        Text(
            text,
            Modifier.align(Alignment.Center),
            style = TextStyle.Default.copy(
                fontSize = 32.sp
            )
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(onLogout = {})
}