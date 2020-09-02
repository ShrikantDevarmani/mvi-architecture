package com.logicsoulllp.classtest.ui.singin

import android.app.Application
import com.logicsoulllp.classtest.R
import com.logicsoulllp.classtest.data.prefs.SharedPrefsConstants
import com.logicsoulllp.moneymanager.prefs.SharedPref
import com.rohitss.aacmvi.AacMviViewModel

/**
 * Sign up view model
 */
class SignInViewModel(application: Application) :
    AacMviViewModel<SignInViewState, SignInEffect, SignInEvent>(application) {

    init {
        viewState = SignInViewState(SignInStates.Initial)
    }

    override fun process(viewEvent: SignInEvent) {
        super.process(viewEvent)
        when (viewEvent) {
            SignInEvent.SignUp -> {
                viewState = viewState.copy(signInStates = SignInStates.SignUp)
            }

            is SignInEvent.SignIn -> {
                if (viewEvent.emailOrPhone.isNotEmpty() && viewEvent.password.isNotEmpty()) {
                    // temp evaluated for check once logged in then not to show the intro screen. show till the uses logs in first time
                    savePrefsData(SharedPrefsConstants.IS_SHOW_SIGNIN, false)
                    savePrefsData(SharedPrefsConstants.IS_INTRO_OPENED, true)
                    //todo check for data for validation and satisfied then call api and on response save to pref and show respective message
                    viewState = viewState.copy(signInStates = SignInStates.SignIn)
                } else {
                    // string can be taken from string file. so way is change the message parameter to int and get like R.string.item and in view get through resource function
                    viewEffect =
                        SignInEffect.ShowSnackBar(message = R.string.email_password_field_empty)
                }
            }

            SignInEvent.ForgotPassword -> {
                //todo forgot password state change
                viewState = viewState.copy(signInStates = SignInStates.ForgotPassword)
            }
        }
    }

    private fun savePrefsData(key: String, value: Boolean) {
        SharedPref.setBooleanParams(getApplication(), key, value)
    }
}