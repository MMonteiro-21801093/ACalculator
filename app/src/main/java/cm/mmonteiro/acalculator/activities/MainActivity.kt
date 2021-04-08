package cm.mmonteiro.acalculator.activities

import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat

import cm.mmonteiro.acalculator.helpers.NavigationManager

import cm.mmonteiro.acalculator.R
import com.google.android.material.navigation.NavigationView

import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupDrawerMenu()
        if(!screenRotated(savedInstanceState)){
            NavigationManager.gotCalculatorFragment(supportFragmentManager)
        }
    }

    private fun screenRotated(savedInstanceState: Bundle?): Boolean {
      return savedInstanceState!=null
    }

    private fun setupDrawerMenu() {
        val toogle = ActionBarDrawerToggle(
            this, drawer, toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )

        nav_drawer.setNavigationItemSelectedListener(this)
       // NavigationManager.gotCalculatorFragment(supportFragmentManager)
        nav_drawer.getMenu().getItem(0).setChecked(true)
        toolbar.title = getResources().getString(R.string.calculator)
        drawer.addDrawerListener(toogle)
        toogle.syncState()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_calculator -> {
                NavigationManager.gotCalculatorFragment(supportFragmentManager)
                formatTitle("calculator")
            }
            R.id.nav_history -> {
                NavigationManager.gotHistoryFragment(supportFragmentManager)
                formatTitle("history")
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    private fun formatTitle(fragment: String) {

        for (i in 0..2) {

            if (fragment == "calculator" && i == 0) {
                nav_drawer.getMenu().getItem(i).setChecked(true)
                toolbar.title = getResources().getString(R.string.calculator)
                continue
            }
            if (fragment == "history" && i == 1) {
                nav_drawer.getMenu().getItem(i).setChecked(true)
                toolbar.title = getResources().getString(R.string.history)
                continue
            }

            nav_drawer.getMenu().getItem(i).setChecked(false)
        }

    }
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }

    }
}

