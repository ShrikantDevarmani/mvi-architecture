package com.logicsoulllp.classtest.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.logicsoulllp.classtest.R
import com.logicsoulllp.classtest.data.prefs.SharedPrefsConstants
import com.logicsoulllp.classtest.helper.IntegerConstants
import com.logicsoulllp.classtest.ui.main.MainActivity
import com.logicsoulllp.classtest.ui.singin.SignInActivity
import com.logicsoulllp.classtest.ui.walkthrough.WalkThroughActivity
import com.logicsoulllp.moneymanager.prefs.SharedPref

/**
 * Splash activity
 * Not used like mvi kept simple as no much complexity
 */
class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // calculated earlier due to blink
        val intent: Intent = if (restorePrefData(SharedPrefsConstants.IS_INTRO_OPENED)) {
            if (restorePrefData(SharedPrefsConstants.IS_SHOW_SIGNIN)) {
                Intent(this, SignInActivity::class.java)
            } else {
                Intent(this, MainActivity::class.java)
            }
        } else {
            Intent(this, WalkThroughActivity::class.java)
        }

        Handler().postDelayed({
            // when this activity is about to be launch we need to check if its opened before or not
            startActivity(intent)
            finish()
        }, IntegerConstants.SPLASH_SCREEN_TIMEOUT)
    }

    private fun restorePrefData(constants: String): Boolean {
        return SharedPref.getBooleanParams(
            this,
            constants
        )
    }
}