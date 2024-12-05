package com.table.tatu.amparo.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.table.tatu.amparo.enums.Relation
import com.table.tatu.amparo.extensions.brName
import com.table.tatu.amparo.models.SupportNetwork
import com.table.tatu.amparo.ui.theme.grandstanderFontFamily

@Composable
fun SupportNetworkCard(supportNetwork: SupportNetwork){
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:+55${supportNetwork.phoneNumber}")
                }
                context.startActivity(intent)
            },
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = supportNetwork.name,
                fontFamily = grandstanderFontFamily,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Telefone: ${supportNetwork.phoneNumber}",
                fontFamily = grandstanderFontFamily,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Relação: ${supportNetwork.relation.brName()}",
                fontFamily = grandstanderFontFamily,
                fontSize = 15.sp
            )
        }
    }
}

@Preview
@Composable
private fun SupportNetworkCardPreview(){
    SupportNetworkCard(
        SupportNetwork("Pedro José", "11948434934", Relation.FRIEND)
    )
}