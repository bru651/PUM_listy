package com.example.lista4.data

import com.example.lista4.data.model.Exercise
import com.example.lista4.data.model.ExerciseList
import com.example.lista4.data.model.Subject
import kotlin.random.Random

object DataProvider {

    val listaZadan = mutableListOf<ExerciseList>()
    var liczbalist:List<Int> = GenIntList()
    val przedmioty = mutableListOf(
        Subject("Matematyka"),
        Subject("Pum"),
        Subject("Fizyka"),
        Subject("Elektronika"),
        Subject("Algorytmy")
    )


    fun GenIntList(): List<Int> {
        val resultList = mutableListOf<Int>()
        var remainSum = 20

        for (i in 1 until 5) {
            // Generate a random number between 1 and the remaining sum, with a maximum of (remainingSum - (5 - i)).
            val randomValue = Random.nextInt(1, minOf(remainSum - (5 - i) + 1,7))
            resultList.add(randomValue)
            remainSum -= randomValue
        }
        // The last item in the list is the remaining sum to ensure the total is 20.
        resultList.add(remainSum)
        return resultList
    }

    fun generuj()
    {



        var temp = ExerciseList(emptyList<Exercise>(), Subject("t"),1.0,0)
        var LiczbaZadan =0


        var noweZadanie = Exercise("tresc",1)
        var zadaniaWliscie = mutableListOf<Exercise>()
        var przedmiot = Subject("a")
        for(i in 0..4)
        {

            for (j in 1..liczbalist[i])
            {
                zadaniaWliscie = mutableListOf<Exercise>()

                LiczbaZadan = Random.nextInt(1,11) //od 1 do 10
                for (a in 1 .. LiczbaZadan)
                {
                    noweZadanie = Exercise("Lorem ipsum times $a",Random.nextInt(1,11))
                    zadaniaWliscie.add(noweZadanie)
                }
                przedmiot = przedmioty[i]
                temp = ExerciseList(zadaniaWliscie, przedmiot,Random.nextInt(6,11)/2.0,j)

                listaZadan.add(temp)
            }
        }
    }

    init {
        generuj()
    }



}