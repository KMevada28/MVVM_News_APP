package com.newsapp.lrf


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.newsapp.R

class LRFActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lrf)

        val host = NavHostFragment.create(R.navigation.nav_graph)
            supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, host)
            .setPrimaryNavigationFragment(host).commit()

        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginFragment())
                .commitNow()
        }*/
    }
}
