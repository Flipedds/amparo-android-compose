package com.table.tatu.amparo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.table.tatu.amparo.R
import com.table.tatu.amparo.ui.theme.amparoDefaultColor
import com.table.tatu.amparo.ui.theme.amparoDenunciaColor
import com.table.tatu.amparo.ui.theme.amparoMenuColor

@Composable
fun MenuBar(){
    Column {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(amparoDefaultColor))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(59.dp)
                .background(amparoMenuColor)
                .padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_home),
                contentDescription = "", Modifier
                    .offset(y = -(4.dp))
                    .align(Alignment.CenterVertically)
                    .clickable {  })
            Icon(
                painter = painterResource(id = R.drawable.ic_account),
                contentDescription = "", Modifier
                    .align(Alignment.CenterVertically)
                    .clickable {  })
            Column(
                Modifier
                    .offset(y = -(20.dp))
                    .align(Alignment.CenterVertically)
                    .clip(RoundedCornerShape(40.dp))
                    .background(amparoDenunciaColor)
                    .size(60.dp, 70.dp)
                    .clickable {  }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_shield),
                    contentDescription = "", Modifier
                        .align(Alignment.CenterHorizontally)
                        .offset(y = 5.dp))
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = "", Modifier
                    .offset(y = -(3.dp))
                    .align(Alignment.CenterVertically)
                    .clickable {  })

            Icon(
                painter = painterResource(id = R.drawable.ic_map),
                contentDescription = "",
                Modifier
                    .align(Alignment.CenterVertically)
                    .offset(y = -(4.dp))
                    .clickable {  })
        }
    }
}

@Preview
@Composable
private fun MenuBarPreview(){
    MenuBar()
}