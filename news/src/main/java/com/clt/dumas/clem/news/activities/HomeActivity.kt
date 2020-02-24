package com.clt.dumas.clem.news.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clt.dumas.clem.news.R
import com.clt.dumas.clem.news.dataSources.database.NewsDatabase
import com.clt.dumas.clem.news.fragments.ArticlesFragment
import com.clt.dumas.clem.news.fragments.FavoritesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val navigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    val fragment = ArticlesFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.articles_fragment_container,
                        fragment,
                        fragment.javaClass.simpleName
                    )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.favorite -> {
                    val fragment = FavoritesFragment()
                    supportFragmentManager.beginTransaction().replace(
                        R.id.articles_fragment_container,
                        fragment,
                        fragment.javaClass.simpleName
                    )
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        NewsDatabase.initNewsDatabase(this)
        bottom_navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        if (savedInstanceState == null) {
            //Display first fragment
            val articlesFragment = ArticlesFragment()
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.articles_fragment_container, articlesFragment)
            }.commit()
        }
    }

}
