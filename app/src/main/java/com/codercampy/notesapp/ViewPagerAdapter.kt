package com.codercampy.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    val arrItems = arrayOf(
        "First Screen",
        "Second Screen",
        "Third Screen"
    )

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val text = arrItems[position]

        val f = IntroContentFragment()
        f.arguments = Bundle().apply {
            putString("name", text)
        }
        return f
    }

}