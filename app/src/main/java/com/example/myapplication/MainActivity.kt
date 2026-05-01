package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game()
        }
    }
}

val buttonList = hashMapOf(
    "A1" to ButtonValue(false,"", 0),
    "A2" to ButtonValue(false,"", 0),
    "A3" to ButtonValue(false,"", 0),
    "B1" to ButtonValue(false,"", 0),
    "B2" to ButtonValue(false,"", 0),
    "B3" to ButtonValue(false,"", 0),
    "C1" to ButtonValue(false,"", 0),
    "C2" to ButtonValue(false,"", 0),
    "C3" to ButtonValue(false,"", 0)
)

var moveSequence : Int = 1

var playerMove: Char = 'X'

fun nextMove(button: String){

    buttonList[button]?.player = playerMove.toString()
    buttonList[button]?.status = true
    buttonList[button]?.sequence = moveSequence
    moveSequence++

    if(moveSequence > 9){
        buttonList.forEach { (string, value) ->

        }
    }

    if(playerMove == 'X'){
        playerMove == 'O'
    } else {
        playerMove == 'X'
    }
}
//
//fun buttonUpdate(){
//
//}

@Composable
fun Game() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Score(10,20)
        ButtonRow("A")
        ButtonRow("B")
        ButtonRow("C")
    }
}

@Composable
fun ButtonRow(row: String){
    Row(){
        SingleButton("1", row)
        SingleButton("2", row)
        SingleButton("3", row)
    }
}

@Composable
fun Score(player1Score: Int, player2Score: Int ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Lewa strona - Player 1
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Player1",
                modifier = Modifier.padding(end = 8.dp),
                color = Color.Red)
            Text(text = player1Score.toString(),
                color = Color.Red)
        }

        // Prawa strona - Player 2
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Player2",
                modifier = Modifier.padding(end = 8.dp),
                color = Color.Blue)
            Text(text = player2Score.toString(),
                color = Color.Blue)
        }
    }
}

@Composable
fun SingleButton(column : String, row: String ){
    Button(onClick = {},
        modifier = Modifier.size(120.dp),
        shape = RoundedCornerShape(10.dp)) {
        Text(text = "X")
    }
}


@Preview
@Composable
fun ShowMeGame(){
    Game()
}



fun CheckScore(){
//    if(
//        (buttonList["A1"]. == true && buttonList["A2"] == true && buttonList["A3"] == true) or
//        (buttonList["B1"] == true && buttonList["B2"] == true && buttonList["B3"] == true) or
//        (buttonList["C1"] == true && buttonList["C2"] == true && buttonList["C3"] == true) or
//        (buttonList["A1"] == true && buttonList["B1"] == true && buttonList["C1"] == true) or
//        (buttonList["A2"] == true && buttonList["B2"] == true && buttonList["C2"] == true) or
//        (buttonList["A3"] == true && buttonList["B3"] == true && buttonList["C3"] == true) or
//        (buttonList["A1"] == true && buttonList["B2"] == true && buttonList["C3"] == true) or
//        (buttonList["A3"] == true && buttonList["B2"] == true && buttonList["C1"] == true)) {
//
//
//    }
}




