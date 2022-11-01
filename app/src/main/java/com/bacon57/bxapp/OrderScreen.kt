package com.bacon57.bxapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OrderScreen(
    state: OrderState,
    onAction: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(8.dp),

        verticalArrangement = Arrangement.SpaceEvenly,
    ) {

        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Table:",
                modifier = Modifier.fillMaxWidth(0.2f),

                )
            Text(
                text = "Mesa 5",
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )
        }
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )

        Row(Modifier.fillMaxWidth()) {
            Text(
                text = "Waiter:",
                modifier = Modifier.fillMaxWidth(0.2f)
            )
            Text(
                text = "Mesero",
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )
        }

        Spacer(
            modifier = Modifier
                .height(16.dp)
                .background(Color.Red)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "---------------------------")
            Text(
                text = "$0000",

                modifier = Modifier.background(Color.Yellow)
                    .padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(Color.Green)
        ) {
            items(10) { index ->
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "1",
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .padding(5.dp),
                        fontSize = 24.sp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(.7f)
                    ) {
                        Text(
                            text = "Producto",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = "Adicionales...",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = "Exclusiones...",
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                    Text(
                        text = "0000",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.White),
                        textAlign = TextAlign.Right
                    )
                }
//                if(index< itemList.lastIndex)
                Divider(color = Color.Black, thickness = 1.dp)
            }
        }
    }

}