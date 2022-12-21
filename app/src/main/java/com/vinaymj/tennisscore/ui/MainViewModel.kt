package com.vinaymj.tennisscore.ui

import androidx.lifecycle.ViewModel
import com.vinaymj.tennisscore.common.cloneMatchObject
import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase
import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase.Players
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: ScoreUpdateUseCase
) : ViewModel() {


    private val _scoreBoardState = MutableStateFlow(ScoreBoardUiState())
    val scoreBoardState: StateFlow<ScoreBoardUiState> = _scoreBoardState.asStateFlow()

    fun updateScore(point_to: Players) {

        val m = cloneMatchObject(_scoreBoardState.value.match)

        val updatedScore = useCase.execute(m.apply { pointTo = point_to })

        _scoreBoardState.update {
            it.copy(
                match = updatedScore
            )
        }
    }

    fun resetMatchScore() {
        _scoreBoardState.value = ScoreBoardUiState()
    }

}