package com.example.myapplication

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Game()
        }
    }
}

var buttonList = mutableStateMapOf(
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

var moveSequence by mutableIntStateOf(1)
var playerMove= "X"

var player1Score :Int by mutableStateOf(0)
var player2Score :Int by mutableStateOf(0)


fun nextMove(button: String){
    val current = buttonList[button] ?: return

    if (!current.status){
        buttonList[button] = current.copy(
            status = true,
            sequence = moveSequence,
            player = playerMove
        )
        if (winner()){
            addScore()
        }else {
            moveSequence++
        }

        playerMove = if (playerMove == "X") "O" else "X"
    }
}

@Composable
fun Game() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Score(player1Score,player2Score)
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
fun SingleButton(column: String, row: String) {
    val key = row + column
    Button(
        onClick = { nextMove(key) },
        modifier = Modifier.size(120.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = buttonList[key]?.player ?: "", fontSize = 15.em)
    }
}


@Preview
@Composable
fun ShowMeGame(){
    Game()
}

fun winner(): Boolean {
    val winPatterns = listOf(
        listOf("A1", "A2", "A3"),
        listOf("B1", "B2", "B3"),
        listOf("C1", "C2", "C3"),
        listOf("A1", "B1", "C1"),
        listOf("A2", "B2", "C2"),
        listOf("A3", "B3", "C3"),
        listOf("A1", "B2", "C3"),
        listOf("A3", "B2", "C1")
    )


    return winPatterns.any { pattern ->
        val players = pattern.mapNotNull { buttonList[it]?.player }

        players.size == 3 &&
                players.first().isNotEmpty() &&
                players.distinct().size == 1
    }
}

fun addScore(){
    if(playerMove.equals("X")){
        player1Score++
    } else {
        player2Score++
    }

    buttonList.forEach { (key, value) ->
        buttonList[key] = value.copy(
            status = false,
            player = "",
            sequence = 0
        )
    }
}




