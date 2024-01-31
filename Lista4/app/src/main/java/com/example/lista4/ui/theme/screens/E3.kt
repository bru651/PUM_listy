package com.example.lista4.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista4.data.DataProvider

@Composable
fun E3(indexPassed: String)
{
    val indexint = indexPassed.toInt()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "${DataProvider.listaZadan[indexint].subject.name}", modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),fontSize=26.sp)
        LazyColumn(
            modifier = Modifier.fillMaxWidth().height(600.dp)
        ) {
            items(DataProvider.listaZadan[indexint].exercise.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .background(Color.Yellow)
                        .height(80.dp)
                ) {
                    //lewygorny
                    Text(
                        text = "Zadanie ${index+1}",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp),
                        fontSize = 26.sp
                    )
                    //prawygorny
                    Text(
                        text = "Pkt: ${DataProvider.listaZadan[indexint].exercise[index].points}",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp),
                        fontSize = 26.sp
                    )

                    //lewydolny
                    Text(
                        text = "${DataProvider.listaZadan[indexint].exercise[index].content}",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                    )
                }
            }
        }



    }
}