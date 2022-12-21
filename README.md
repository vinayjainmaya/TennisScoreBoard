# TennisScoreBoard
Simple application to demonstrate the Tennis scoreboard best of three.

# Rules
### Game points
- 4 points are one Set.
- The score of each game is from 0 to 4 points are described as `love`, `15`, `30`, `40`, and `game`.
- if both players score 40-40 then it's a `deuce`.
- if it's `deuce`, then the player needs to win with a difference of 2.
- when it's `deuce` and if the player got one point then it's `advantage`.
- when it's `deuce` and each player won one-one point it's `deuce` again.

### Setpoints.
- 6 Sets are one Match.
- if both players score 6-6, then it's a `tiebreak` (tiebreak scenario is not handled as of now).

### Match points
- if it's the best of 3, a player needs to score 2 matches in total to win.

# Application Screens
![reset_screen](https://github.com/vinayjainmaya/TennisScoreBoard/blob/master/screen/reset.png?raw=true "Reset Screen")
![advantage_screen](https://github.com/vinayjainmaya/TennisScoreBoard/blob/master/screen/ad.png?raw=true "Advantage Screen")
![match_win_screen](https://github.com/vinayjainmaya/TennisScoreBoard/blob/master/screen/match_win.png?raw=true "Match Win Screen")

### Libraries
- [Jetpack](https://developer.android.com/jetpack)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI data to survive configuration changes.
    - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - is a state-holder observable flow that emits the current and new state updates to its collectors.
    - [Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is the modern toolkit for building native Android UI
- [Hilt](https://dagger.dev/hilt/) - A fast dependency injector for Android.



