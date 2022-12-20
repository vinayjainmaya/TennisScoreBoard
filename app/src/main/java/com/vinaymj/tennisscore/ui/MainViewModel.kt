package com.vinaymj.tennisscore.ui

import androidx.lifecycle.ViewModel
import com.vinaymj.tennisscore.domain.ScoreUpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    val useCase: ScoreUpdateUseCase
) : ViewModel() {


}