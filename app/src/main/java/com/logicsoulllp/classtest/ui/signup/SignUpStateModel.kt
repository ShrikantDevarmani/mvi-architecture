package com.logicsoulllp.classtest.ui.signup

// this is like partial view state contain multiple states as parameter for different tasks like below one
data class SignUpViewState(
    val signUpStates: SignUpStates
)

sealed class SignUpEffect {
    data class ShowSnackBar(val message: String) : SignUpEffect()
    data class ShowToast(val message: String) : SignUpEffect()
}

sealed class SignUpEvent {
    data class Submit(val position: Int) : SignUpEvent()
}

sealed class SignUpStates {
    object SignUp : SignUpStates()
    data class EmptyFieldError(val error: String) : SignUpStates()
}