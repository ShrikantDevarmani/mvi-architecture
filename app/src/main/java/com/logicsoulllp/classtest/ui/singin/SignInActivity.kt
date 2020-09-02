package com.logicsoulllp.classtest.ui.singin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.logicsoulllp.classtest.R
import com.logicsoulllp.classtest.databinding.ActivitySignInBinding
import com.logicsoulllp.classtest.helper.toast
import com.logicsoulllp.classtest.ui.forgot_password.ForgotPasswordActivity
import com.logicsoulllp.classtest.ui.main.MainActivity
import com.logicsoulllp.classtest.ui.signup.SignUpActivity
import com.rohitss.aacmvi.AacMviActivity

class SignInActivity :
    AacMviActivity<SignInViewState, SignInEffect, SignInEvent, SignInViewModel>() {

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        binding.btnSignUp.setOnClickListener {
            viewModel.process(SignInEvent.SignUp)
        }

        binding.btnSignIn.setOnClickListener {
            viewModel.process(
                SignInEvent.SignIn(
                    binding.emailOrPhone.text.toString(),
                    binding.password.text.toString()
                )
            )
        }

        binding.btnForgotPassword.setOnClickListener {
            viewModel.process(SignInEvent.ForgotPassword)
        }
    }

    override val viewModel: SignInViewModel by viewModels()

    override fun renderViewState(viewState: SignInViewState) {
        when (viewState.signInStates) {
            is SignInStates.Initial -> {
                //todo initial states if of view / or on initial condition
            }
            is SignInStates.EmptyFieldError -> {
                //todo show snack bar or with respect to view as error
                toast(viewState.signInStates.error)
            }
            is SignInStates.SignIn -> {
                //todo temp evaluated
                finishAffinity()
                MainActivity.startAct(this)
            }
            is SignInStates.SignUp -> {
                SignUpActivity.startAct(this)
            }
            is SignInStates.ForgotPassword -> {
                ForgotPasswordActivity.startAct(this)
            }
        }
    }

    override fun renderViewEffect(viewEffect: SignInEffect) {
        when (viewEffect) {
            is SignInEffect.ShowSnackBar -> {
                Snackbar.make(
                    binding.constraintLayoutRoot,
                    viewEffect.message,
                    Snackbar.LENGTH_LONG
                ).show()
            }
            is SignInEffect.ShowToast -> {
                toast(message = resources.getString(viewEffect.message))
            }
        }
    }

    companion object {
        fun startAct(context: Context) {
            val mainActivity = Intent(context, SignInActivity::class.java)
            context.startActivity(mainActivity)
        }
    }
}