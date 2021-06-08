package com.example.garda.viewpager



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.garda.detailfragment.AboutFragment
import com.example.garda.detailfragment.HarvestFragment
import com.example.garda.detailfragment.InstructionFragment
import com.example.garda.detailfragment.JournalFragment

class SectionPagerAdapter(activity: AppCompatActivity, data:Bundle) : FragmentStateAdapter(activity)  {

    private var fragmentBundle : Bundle = data

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = AboutFragment()
            1 -> fragment = InstructionFragment()
            2 -> fragment = HarvestFragment()
            3 -> fragment = JournalFragment()
        }
        fragment?.arguments=this.fragmentBundle
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 4
    }
}