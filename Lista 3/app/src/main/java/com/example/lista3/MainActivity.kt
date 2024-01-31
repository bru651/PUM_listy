package com.example.lista3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lista3.ui.theme.Lista3Theme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {

    private var questioncount by mutableStateOf(false)
    private var pointc by mutableStateOf(0)

    var selectedOption = ""
    private var pytania = listOf(
        Question("2+2", "4", "5", "3", "Tak jak partia mówi", 0),
        Question("Polska jest członkiem:","NATO","Uni Europejskiej","NATO i EU","Paktu Warszawskiego",2),
        Question("Czy ten quiz jest trudny","Tak","Bardzo","Jak najbardziej","Nie",3),
        Question("1/3","0.3","0.(3)","0.3333333333333","0.300000...9",1),
        Question("KKK to","Hinduska bojówka nacjonalistyczna","Kaszubskie Kotlety Kute","Producent czapek dla smerfów","Rasistowska organicacja promująca białą wyższość",3),
        Question("Kto jest naszym największym sąsiadem","Kalingrad","Rosja","Moskwa","Niemcy",1),
        Question("Poprawna odpowiedź to 3","1","2","3","4",2),
        Question("Google najbardziej jest znany jako","Wyszukiwarka","Media społecznościowe","Kantor","Organizacja szukająca leku na raka",0),
        Question("By zaliczeyć listę trzeba:","Tylko ją wysłać do prowadzącego","Pokazać na zajęciach że aplikacja działa","Zrobić o niej prezentację","Mieć udowodnioną obecność na wykładzie",1),
        Question("To ostatnie pytanie, tak?","Nie","Jeszcze dziesięć","Nie ma możliwości stwierdzenia","Tak",3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContent {
            Lista3Theme {
                if (questioncount == false) {
                    main(
                        selectedOption,
                        onNextQuestion = {
                            questioncount = true
                        },
                        punkty = {
                            pointc+=10
                        }
                    ,pytania)
                } else {
                    scoreScreen(pointc)
                }
            }
        }
    }
}


data class Question(val que:String="",val ansp:String="",val ansd:String="",val anst:String="",val ansc:String="",val cor:Int=0)

@Composable
fun scoreScreen(corectCounter: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Gratulacje",
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Card(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = "Punkty $corectCounter",
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}


@Composable
fun main(selectedOption: String,onNextQuestion: () -> Unit, punkty: () -> Unit,pytania: List<Question>)
{
    var nrpytania by remember { mutableStateOf(0) }
    var correctAnswers by remember { mutableStateOf(0) }
    var isgood by remember { mutableStateOf(false) }



    LazyColumn{
        item{
            Greeting(numerPytania = nrpytania+1)
        }
        item{
            Progressbar(wartosc = nrpytania)
        }
        item{
            CardS(numerPytania = nrpytania,pytania=pytania)
        }
        item {Answers(pytania, numerPytania = nrpytania, onOptionSelected = { selectedOption -> if(checkAnswer(pytania,selectedOption,nrpytania)){isgood = true}else{isgood = false} }
        )
        }
        item {
            Nastepne(
                selectedOption = selectedOption,
                onNextQuestion = {

                    if(isgood)
                    {
                        correctAnswers++
                        punkty()
                    }
                    if (nrpytania < 9) {
                        nrpytania++
                    } else {
                        onNextQuestion()
                    }
                    println("questionNumber: $nrpytania")
                }
            )
        }

    }
}

fun checkAnswer(pytania: List<Question>, wybrane: Int, qCounter: Int): Boolean {

    return wybrane == pytania[qCounter].cor
}

@Composable
fun Greeting(numerPytania: Int, modifier: Modifier = Modifier) {
    Text(
        text = "Pytanie $numerPytania / 10",
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        fontSize = 32.sp,
        textAlign = TextAlign.Center


    )
}
@Composable
fun CardS(pytania: List<Question>,numerPytania: Int,modifier: Modifier = Modifier) {

    Card(
        modifier = Modifier.height(150.dp).fillMaxWidth().padding(16.dp)
    ) {
        Text(text = pytania[numerPytania].que,modifier = modifier.fillMaxSize().padding(30.dp), textAlign = TextAlign.Center,)

    }
}



@Composable
fun Answers(pytania: List<Question>, numerPytania: Int, modifier: Modifier = Modifier, onOptionSelected: (Int) -> Unit) {
    var wybrane by remember { mutableStateOf(pytania[numerPytania].ansp) }
    Card(modifier = modifier.fillMaxWidth()//.padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()//.padding(16.dp)
        ) {


            RadioButtonF(pytania[numerPytania].ansp, wybrane == pytania[numerPytania].ansp) {
                wybrane = pytania[numerPytania].ansp
                onOptionSelected(0)
            }

            RadioButtonF(pytania[numerPytania].ansd, wybrane == pytania[numerPytania].ansd) {
                wybrane = pytania[numerPytania].ansd
                onOptionSelected(1)
            }

            RadioButtonF(pytania[numerPytania].anst, wybrane == pytania[numerPytania].anst) {
                wybrane = pytania[numerPytania].anst
                onOptionSelected(2)
            }
            RadioButtonF(pytania[numerPytania].ansc, wybrane == pytania[numerPytania].ansc) {
                wybrane = pytania[numerPytania].ansc
                onOptionSelected(3)
            }

        }
    }
}

@Composable
fun RadioButtonF(text: String, wybrane: Boolean, onSelected: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically)
    {
        RadioButton(selected = wybrane, onClick = { onSelected(text) }, modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = text)
    }
}

@Composable
fun Nastepne(selectedOption: String, onNextQuestion: () -> Unit) {
    Button(onClick = { onNextQuestion() }, modifier = Modifier.padding(16.dp).fillMaxWidth().wrapContentSize(Alignment.BottomCenter))
    {
        Text(text = "Następne")
    }
}


@Composable
fun Progressbar(wartosc: Int)
{
    LinearProgressIndicator(progress = wartosc.toFloat() / 10.0f, modifier = Modifier.padding(16.dp).fillMaxSize().wrapContentSize(Alignment.Center)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lista3Theme {
        Greeting(0)
    }
}