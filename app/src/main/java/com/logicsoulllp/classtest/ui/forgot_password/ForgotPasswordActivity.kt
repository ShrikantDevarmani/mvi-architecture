package com.logicsoulllp.classtest.ui.forgot_password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.logicsoulllp.classtest.R
import com.rohitss.aacmvi.AacMviActivity

class ForgotPasswordActivity :
    AacMviActivity<ForgotPasswordStates, ForgotPasswordEffect, ForgotPasswordEvent, ForgotPasswordViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }

    override val viewModel: ForgotPasswordViewModel by viewModels()

    override fun renderViewState(viewState: ForgotPasswordStates) {

    }

    override fun renderViewEffect(viewEffect: ForgotPasswordEffect) {

    }

    companion object {
        fun startAct(context: Context) {
            val passwordActivity = Intent(context, ForgotPasswordActivity::class.java)
            context.startActivity(passwordActivity)
        }
    }
}