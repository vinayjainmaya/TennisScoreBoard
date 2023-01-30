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
import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.PointTo
import com.vinaymj.tennisscore.ui.theme.Purple700


@Composable
fun ScoreBoardScreen(
    modifier: Modifier = Modifier,
    mainViewModel: MainViewModel = viewModel(),
) {
    val scoreBoard by mainViewModel.scoreBoardState.collectAsState()

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        // Result section
        if (scoreBoard.match.someBodyWin) {
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
        stringResource(if (scoreBoard.match.playerAScore.wins()) R.string.player_a else R.string.player_b)),
        modifier = modifier.padding(8.dp).testTag("matchResult"),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
}

@Composable
fun ScoreTableSection(modifier: Modifier, scoreBoard: ScoreBoardUiState) {
    Row(modifier = modifier.padding(8.dp)) {
        // Player Section
        Column(modifier = modifier.padding(8.dp)) {

            DrawTableHeader(modifier)
            DrawTableHeader(modifier, R.string.player_a, "rowHeadPlayerA")
            DrawTableHeader(modifier, R.string.player_b, "rowHeadPlayerB")

        }

        // Match Section
        Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            DrawTableHeader(modifier,R.string.match)
            DrawTableCell(modifier, scoreBoard.match.playerAScore.matchPoint.toString(), "playerAMatchPoint")
            DrawTableCell(modifier, scoreBoard.match.playerBScore.matchPoint.toString(), "playerBMatchPoint")
        }

        // Set Section
        Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            DrawTableHeader(modifier,R.string.set)
            DrawTableCell(modifier, scoreBoard.match.playerAScore.setPoint.toString(), "playerASetPoint")
            DrawTableCell(modifier, scoreBoard.match.playerBScore.setPoint.toString(), "playerBSetPoint")
        }

        // Point Section
        Column(modifier = modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            DrawTableHeader(modifier,R.string.point)
            DrawTableCell(modifier, scoreBoard.match.playerAScore.gamePoint, "playerAGamePoint")
            DrawTableCell(modifier, scoreBoard.match.playerBScore.gamePoint, "playerBGamePoint")
        }
    }
}

@Composable
fun DrawTableCell(modifier: Modifier, value: String, tagValue: String) {
    Text(text = stringResource(R.string.string_placeholder, value),
        modifier = modifier.padding(8.dp).testTag(tagValue),
        fontSize = 20.sp)
}

@Composable
private fun DrawTableHeader(modifier: Modifier, resourceId: Int = R.string.string_empty, tagValue: String = "") {
    Text(text = stringResource(resourceId),
        modifier = modifier.padding(8.dp).testTag(tagValue),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Purple700)
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
            mainViewModel.updateScore(PointTo.A)
        }) {
            Text(text = stringResource(id = R.string.player_a))
        }

        Button(
            modifier = modifier
                .padding(24.dp, 8.dp, 8.dp, 8.dp)
                .testTag("playerBButton"),
            onClick = {
            mainViewModel.updateScore(PointTo.B)
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
