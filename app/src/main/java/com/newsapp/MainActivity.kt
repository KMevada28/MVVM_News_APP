package com.newsapp

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.newsapp.dashboard.PlaceholderFragment
import com.newsapp.utils.Constants.Companion.COUNTRY
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityPostListBinding

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private lateinit var binding: com.newsapp.databinding.ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        // Give the TabLayout the ViewPager
        val tabLayout: TabLayout = findViewById(R.id.sliding_tabs)
        tabLayout.setupWithViewPager(container)

    }
    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(COUNTRY, getData()[position])
        }

        override fun getCount(): Int {
            return getData().size
        }


        override fun getPageTitle(position: Int): CharSequence? {
            return getData()[position]
        }

        private fun getData(): ArrayList<String> {
            val listCategory: ArrayList<String> = ArrayList()
            listCategory.add("business")
            listCategory.add("entertainment")
            listCategory.add("general")
            listCategory.add("health")
            listCategory.add("science")
            listCategory.add("sports")
            listCategory.add("technology")
            return listCategory
        }
    }


}
