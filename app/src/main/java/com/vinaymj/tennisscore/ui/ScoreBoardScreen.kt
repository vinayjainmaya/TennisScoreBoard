package com.vinaymj.tennisscore.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vinaymj.tennisscore.R
import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.Players
import com.vinaymj.tennisscore.ui.theme.Purple700


@Composable
fun ScoreBoardScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel(),
) {
    val scoreBoard by mainViewModel.scoreBoardState.collectAsState()

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // table section
        if (scoreBoard.match.ifSomeBodyWins()) {
            ResultSection(modifier, scoreBoard)
        }
        // table section
        ScoreTableSection(modifier, scoreBoard)

        // Button Section
        ButtonSection(modifier, mainViewModel)
    }
}

@Composable
fun ResultSection(modifier: Modifier, scoreBoard: ScoreBoardUiState) {
    Text(text = stringResource(R.string.result_string,
        stringResource(if (scoreBoard.match.playerA.wins()) R.string.player_a else R.string.player_b)),
        modifier = modifier.padding(8.dp).testTag("matchResult"),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp)
}

@Composable
fun ScoreTableSection(modifier: Modifier, scoreBoard: ScoreBoardUiState) {
    Row(modifier = modifier.padding(8.dp)) {
        // Player Section
        Column(modifier = modifier.padding(8.dp)) {
            Text(text = "",
                modifier = modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp)
            Text(text = stringResource(R.string.player_a),
                modifier = modifier.padding(8.dp)
                    .testTag("rowHeadPlayerA"),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Purple700)
            Text(text = stringResource(R.string.player_b),
                modifier = modifier.padding(8.dp)
                    .testTag("rowHeadPlayerB"),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Purple700)
        }

        // Match Section
        Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.match),
                modifier = modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Purple700)
            Text(text = stringResource(R.string.string_placeholder,
                scoreBoard.match.playerA.score.matchPoint),
                modifier = modifier.padding(8.dp)
                    .testTag("playerAMatchPoint"),
                fontSize = 20.sp)
            Text(text = stringResource(R.string.string_placeholder,
                scoreBoard.match.playerB.score.matchPoint),
                modifier = modifier.padding(8.dp)
                    .testTag("playerBMatchPoint"),
                fontSize = 20.sp)
        }

        // Set Section
        Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.set),
                modifier = modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Purple700)
            Text(text = stringResource(R.string.string_placeholder,
                scoreBoard.match.playerA.score.setPoint),
                modifier = modifier.padding(8.dp)
                    .testTag("playerASetPoint"),
                fontSize = 20.sp)
            Text(text = stringResource(R.string.string_placeholder,
                scoreBoard.match.playerB.score.setPoint),
                modifier = modifier.padding(8.dp)
                    .testTag("playerBSetPoint"),
                fontSize = 20.sp)
        }

        // Point Section
        Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(R.string.point),
                modifier = modifier.padding(8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Purple700)
            Text(text = stringResource(R.string.string_placeholder,
                scoreBoard.match.displayGamePoint(scoreBoard.match.playerA.score.gamePoint)),
                modifier = modifier.padding(8.dp)
                    .testTag("playerAGamePoint"),
                fontSize = 20.sp)
            Text(text = stringResource(R.string.string_placeholder,
                scoreBoard.match.displayGamePoint(scoreBoard.match.playerB.score.gamePoint)),
                modifier = modifier.padding(8.dp)
                    .testTag("playerBGamePoint"),
                fontSize = 20.sp)
        }
    }
}

@Composable
fun ButtonSection(modifier: Modifier, mainViewModel: MainViewModel) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.Center) {
        OutlinedButton(
            modifier = modifier
                .padding(8.dp, 8.dp, 24.dp, 8.dp)
                .testTag("playerAButton"),
            onClick = {
            mainViewModel.updateScore(Players.A)
        }) {
            Text(text = stringResource(id = R.string.player_a))
        }

        Button(
            modifier = modifier
                .padding(24.dp, 8.dp, 8.dp, 8.dp)
                .testTag("playerBButton"),
            onClick = {
            mainViewModel.updateScore(Players.B)
        }) {
            Text(text = stringResource(id = R.string.player_b))
        }
    }
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.Center) {
        Button(
            modifier = modifier.padding(8.dp).testTag("resetButton"),
            onClick = {
            mainViewModel.resetMatchScore()
        }) {
            Text(text = stringResource(id = R.string.reset))
        }
    }
}
