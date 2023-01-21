package com.bacon57.bxapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun TableListScreen(

) {
    val mContext = LocalContext.current
    LazyVerticalGrid(

        columns = GridCells.Adaptive(128.dp),

        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(20) { index ->
                Card(
                    backgroundColor = colorResource(id = R.color.purple_200),
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .clickable {
                            mToast(mContext, index)
                        },
                    elevation = 8.dp,
                ) {
                    ConstraintLayout(Modifier.fillMaxSize()) {
                        val (boxTitulo, boxWaiter, boxStatus) = createRefs()
                        Text(
                            text = "Mesa ${index + 1}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,

                            color = Color(0xFFFFFFFF),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp).constrainAs(boxTitulo){
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }

                        )
                        Text(
                            text = "Mesero",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color(0xFFA00000),
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(4.dp).constrainAs(boxWaiter){
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                            }
                        )
                        Text(
                            text = "st",
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color(0xFFD1F3C6),
                            textAlign = TextAlign.Left,
                            modifier = Modifier.padding(4.dp).background(Color.Green, shape = RoundedCornerShape(3.dp)).constrainAs(boxStatus){
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            }
                        )
                    }

                }
            }
        })
}

private fun mToast(context: Context, index: Int) {
    Toast.makeText(context, "Selected mesa ${index + 1}", Toast.LENGTH_LONG).show()
}
