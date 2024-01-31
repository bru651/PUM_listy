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
import com.example.lista4.data.DataProvider.przedmioty

@Composable
fun E2()
{

    val tasksBySubject = DataProvider.listaZadan.groupBy { it.subject }
    val subjectsWithAverageGrade = tasksBySubject.mapValues { (_, tasks) ->
        val averageGrade = tasks.map { it.grade }.average()
        String.format("%.1f", averageGrade)
    }


    val subjectListCount = tasksBySubject.mapValues { (_, tasks) ->
        tasks.map { it.subject }.count()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Moje Oceny", modifier = Modifier.padding(10.dp).align(Alignment.CenterHorizontally),fontSize=26.sp)
        LazyColumn(
            modifier = Modifier.fillMaxWidth().height(600.dp)
        ) {
            items(5) { index ->
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                        .background(Color.Yellow)
                        .height(80.dp)

                ) {


                    //lewygorny
                    Text(
                        text = przedmioty[index].name,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp),
                        fontSize = 26.sp
                    )

                    //prawygorny
                    Text(
                        text = "Średnia ${subjectsWithAverageGrade[przedmioty[index]]} ",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp),
                        fontSize = 26.sp
                    )



                    //lewydolny
                    Text(
                        text = "Ilość list: ${subjectListCount[przedmioty[index]]}",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                    )
                }
            }
        }



    }
}