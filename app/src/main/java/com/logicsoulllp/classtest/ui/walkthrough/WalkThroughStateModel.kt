package com.logicsoulllp.classtest.ui.walkthrough

// this is like partial view state contain multiple states as parameter for different tasks like below one
data class WalkThroughViewState(
    val walkThroughStates: WalkThroughStates
)

sealed class WalkThroughEffect {
    data class ShowSnackBar(val message: String) : WalkThroughEffect()
    data class ShowToast(val message: String) : WalkThroughEffect()
}

sealed class WalkThroughEvent {
    object GetStarted : WalkThroughEvent()
    object Skip : WalkThroughEvent()
    data class OnTabSelected(val position: Int) : WalkThroughEvent()
}

sealed class WalkThroughStates {
    object Idle : WalkThroughStates()
    object GetStarted : WalkThroughStates()
    object Skip : WalkThroughStates()
    data class TabSelected(val position: Int) : WalkThroughStates()
}