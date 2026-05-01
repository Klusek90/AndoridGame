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
import androidx.compose.runtime.setValue
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

val buttonList = androidx.compose.runtime.mutableStateMapOf(
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

var moveSequence by androidx.compose.runtime.mutableIntStateOf(1)
var playerMove by androidx.compose.runtime.mutableStateOf("X")

fun nextMove(button: String){
    buttonList[button]?.status = true
    buttonList[button]?.sequence = moveSequence

    if(winner()){
        buttonList.forEach { _, value ->
           value.sequence = 0
           value.status = false
           value.player = ""
        }

    } else {
        moveSequence++
        if (moveSequence > 9) {
            val key = buttonList.filter { it.value.sequence != 0 }
                .minByOrNull { it.value.sequence }
                ?.key
            buttonList[key]?.sequence = 0
            buttonList[key]?.player = ""
            buttonList[key]?.status = false
        }

        if (playerMove == "X") {
            playerMove = "O"
        } else {
            playerMove = "X"
        }
    }
}

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
fun SingleButton(column: String, row: String) {

    val key = row + column
    val content = buttonList[key]

    Button(
        onClick = { buttonUpdate(key) },
        modifier = Modifier.size(120.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(text = content?.player ?: "")
    }
}

fun buttonUpdate(key: String) {
    val thisButton = buttonList[key] ?: return

    if (!thisButton.status) {
        buttonList[key] = thisButton.copy(
            status = true,
            player = playerMove,
            sequence = moveSequence
        )

        nextMove(key)
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
        players.isNotEmpty() && players.distinct().size == 1
    }
}




