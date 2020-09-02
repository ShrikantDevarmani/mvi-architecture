package com.logicsoulllp.classtest.ui.walkthrough

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.logicsoulllp.classtest.R
import com.logicsoulllp.classtest.databinding.ActivityWalkthroughBinding
import com.logicsoulllp.classtest.helper.toast
import com.logicsoulllp.classtest.ui.singin.SignInActivity
import com.rohitss.aacmvi.AacMviActivity

/**
 * On Boarding activity
 * reference : https://github.com/aws1994/IntroScreen
 */
class WalkThroughActivity :
    AacMviActivity<WalkThroughViewState, WalkThroughEffect, WalkThroughEvent, WalkThroughViewModel>() {

    private lateinit var binding: ActivityWalkthroughBinding
    private var walkThroughViewPagerAdapter: WalkThroughViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_walkthrough)

        walkThroughViewPagerAdapter = WalkThroughViewPagerAdapter(this, viewModel.mList)
        binding.screenViewpager.adapter = walkThroughViewPagerAdapter
        binding.screenViewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                viewModel.process(WalkThroughEvent.OnTabSelected(position))
            }

            override fun onPageSelected(position: Int) {}
        })

        // setup tab layout with viewpager
        binding.tabIndicator.setupWithViewPager(binding.screenViewpager)

        binding.btnGetStarted.setOnClickListener {
            viewModel.process(WalkThroughEvent.GetStarted)
        }

        // skip button click listener
        binding.tvSkip.setOnClickListener {
            viewModel.process(WalkThroughEvent.Skip)
        }
    }

    override val viewModel: WalkThroughViewModel by viewModels()

    override fun renderViewState(viewState: WalkThroughViewState) {
        when (viewState.walkThroughStates) {
            is WalkThroughStates.Idle -> {
                //nothing to do
            }
            is WalkThroughStates.Skip -> {
                binding.screenViewpager.currentItem = viewModel.mList.size
            }
            is WalkThroughStates.GetStarted -> {
                //todo clear stack and place sign in activity as top activity in stack
                SignInActivity.startAct(this)
                finish()
            }
            is WalkThroughStates.TabSelected -> {
                if (viewState.walkThroughStates.position == viewModel.mList.size - 1) {
                    binding.btnGetStarted.visibility = View.VISIBLE
                } else {
                    binding.btnGetStarted.visibility = View.GONE
                }
            }
        }
    }

    override fun renderViewEffect(viewEffect: WalkThroughEffect) {
        when (viewEffect) {
            is WalkThroughEffect.ShowSnackBar -> {
                Snackbar.make(
                    binding.constraintLayoutRoot,
                    viewEffect.message,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            is WalkThroughEffect.ShowToast -> {
                toast(message = viewEffect.message)
            }
        }
    }

    companion object {
        fun startAct(context: Context) {
            val walkThroughActivity = Intent(context, WalkThroughActivity::class.java)
            context.startActivity(walkThroughActivity)
        }
    }
}