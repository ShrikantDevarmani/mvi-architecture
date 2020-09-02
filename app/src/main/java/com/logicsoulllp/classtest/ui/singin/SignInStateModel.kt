package com.logicsoulllp.classtest.ui.singin

// this is like partial view state contain multiple states as parameter for different tasks like below one
data class SignInViewState(
    val signInStates: SignInStates
)

sealed class SignInEffect {
    data class ShowSnackBar(val message: Int) : SignInEffect()
    data class ShowToast(val message: Int) : SignInEffect()
}

sealed class SignInEvent {
    data class SignIn(val emailOrPhone: String, val password: String) : SignInEvent()
    object SignUp : SignInEvent()
    object ForgotPassword : SignInEvent()
}

sealed class SignInStates {
    object Initial : SignInStates()
    object SignIn : SignInStates()
    object SignUp : SignInStates()
    object ForgotPassword : SignInStates()
    data class EmptyFieldError(val error: String) : SignInStates()
}