package com.logicsoulllp.classtest.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.logicsoulllp.classtest.R
import com.logicsoulllp.classtest.databinding.ActivitySignUpBinding
import com.logicsoulllp.classtest.ui.forgot_password.ForgotPasswordActivity
import com.rohitss.aacmvi.AacMviActivity

class SignUpActivity :
    AacMviActivity<SignUpViewState, SignUpEffect, SignUpEvent, SignUpViewModel>() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // make the activity on full screen
        //requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
    }

    override val viewModel: SignUpViewModel by viewModels()

    override fun renderViewState(viewState: SignUpViewState) {}

    override fun renderViewEffect(viewEffect: SignUpEffect) {}

    companion object {
        fun startAct(context: Context) {
            val signUpActivity = Intent(context, SignUpActivity::class.java)
            context.startActivity(signUpActivity)
        }
    }
}