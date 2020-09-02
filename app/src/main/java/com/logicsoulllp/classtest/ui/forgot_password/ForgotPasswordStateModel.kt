package com.logicsoulllp.classtest.ui.forgot_password

// this is like partial view state contain multiple states as parameter for different tasks like below one
data class ForgotPasswordViewState(
    val forgotPasswordStates: ForgotPasswordStates
)

sealed class ForgotPasswordEffect {
    data class ShowSnackBar(val message: String) : ForgotPasswordEffect()
    data class ShowToast(val message: String) : ForgotPasswordEffect()
}

sealed class ForgotPasswordEvent {
    object ForgotPasswordRecover : ForgotPasswordEvent()
}

sealed class ForgotPasswordStates {
    object Recover : ForgotPasswordStates()
    data class EmptyFieldError(val error: String) : ForgotPasswordStates()
}