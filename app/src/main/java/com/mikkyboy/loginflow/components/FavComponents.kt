package com.mikkyboy.loginflow.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikkyboy.loginflow.R
import com.mikkyboy.loginflow.ui.theme.Primary
import com.mikkyboy.loginflow.ui.theme.Purple200
import com.mikkyboy.loginflow.ui.theme.Purple500
import com.mikkyboy.loginflow.ui.theme.WhiteColor

@Composable
fun FavAppBar(title: String = "Title", navigationIconClicked: () -> Unit) {
    TopAppBar(
        backgroundColor = Primary,
        navigationIcon = {
            IconButton(onClick = {
                navigationIconClicked.invoke()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.menu),
                    tint = WhiteColor
                )
            }
        },
        title = {
            Text(
                text = title, color = WhiteColor
            )
        },
        actions = {}
    )
}

@Composable
fun ItemCard(cryptoName: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Purple200)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                Box() {
                    Text(text = cryptoName[0].toString())
                }
                Spacer(modifier = Modifier.width(20.dp))
                Column() {
                    Text(cryptoName)
                    //                Text("-1.54%")
                }
            }
            Column() {
                Text(
                    "$270", style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text("-1.54%")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}