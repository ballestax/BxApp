package com.bacon57.bxapp

import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(8.dp)
    ) {

        Greetins()
        ChipSection(chips = listOf("Chip1", "Chip2", "Chip 3"))
    }
}

@Composable
fun Greetins(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Bienvenido usuario",
        fontSize = 24.sp,
        fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun ChipSection(
    chips: List<String>
){
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }
    LazyRow{
        items(chips.size){
            Box(modifier = Modifier
                .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                .clickable { selectedChipIndex = it }
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (selectedChipIndex == it) {
                        colorResource(id = R.color.purple_500)
                    } else {
                        colorResource(id = R.color.purple_200)
                    }
                )
                .padding(15.dp)
            ){
                Text(text= chips[it], color = Color.White)
            }

        }
    }

}