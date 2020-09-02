package com.logicsoulllp.classtest.ui.walkthrough

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.logicsoulllp.classtest.R
import com.logicsoulllp.classtest.databinding.IntroLayoutScreenBinding
import com.logicsoulllp.classtest.model.ScreenItem

/**
 * Walk through view adapter
 */
class WalkThroughViewPagerAdapter(
    var mContext: Context,
    var mListScreen: List<ScreenItem>
) : PagerAdapter() {

    lateinit var binding: IntroLayoutScreenBinding

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.intro_layout_screen, null, false)
        binding.introTitle.text = mListScreen[position].title
        binding.introDescription.text = mListScreen[position].description
        binding.introImg.setImageResource(mListScreen[position].screenImg)
        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int {
        return mListScreen.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun destroyItem(
        container: ViewGroup, position: Int,
        `object`: Any
    ) {
        container.removeView(`object` as View)
    }
}