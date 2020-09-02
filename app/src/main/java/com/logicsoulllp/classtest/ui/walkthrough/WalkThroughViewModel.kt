package com.logicsoulllp.classtest.ui.walkthrough

import android.app.Application
import com.logicsoulllp.classtest.R
import com.logicsoulllp.classtest.model.ScreenItem
import com.rohitss.aacmvi.AacMviViewModel
import java.util.*

/**
 * On-boarding view model
 */
class WalkThroughViewModel(application: Application) :
    AacMviViewModel<WalkThroughViewState, WalkThroughEffect, WalkThroughEvent>(application) {

    // fill list screen
    val mList: MutableList<ScreenItem> = ArrayList()

    init {
        viewState =
            WalkThroughViewState(
                walkThroughStates = WalkThroughStates.Idle
            )

        mList.add(
            ScreenItem(
                "Objective Question test",
                "Test to be taken in the form of objective question pattern while students are learning from home.",
                R.drawable.img1
            )
        )
        mList.add(
            ScreenItem(
                "Faculty on Training",
                "It's easier for faculty to assess and let the student know their result on the way immediately. Assessing becomes easier",
                R.drawable.img1
            )
        )
        mList.add(
            ScreenItem(
                "Study Material",
                "A Study material can be shared by faculty in the form of pdf to students and student can use those study material to study their own",
                R.drawable.img1
            )
        )
    }

    override fun process(viewEvent: WalkThroughEvent) {
        super.process(viewEvent)
        when (viewEvent) {
            WalkThroughEvent.GetStarted -> getStarted()
            WalkThroughEvent.Skip -> skip()
            is WalkThroughEvent.OnTabSelected -> onTabSelected(viewEvent.position)
        }
    }

    private fun onTabSelected(position: Int) {
        viewState = viewState.copy(walkThroughStates = WalkThroughStates.TabSelected(position))
    }

    private fun skip() {
        viewState = viewState.copy(walkThroughStates = WalkThroughStates.Skip)
    }

    private fun getStarted() {
        viewState = viewState.copy(walkThroughStates = WalkThroughStates.GetStarted)
    }
}