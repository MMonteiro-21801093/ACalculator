package cm.mmonteiro.acalculator.helpers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cm.mmonteiro.acalculator.views.calculator.CalculatorFragment
import cm.mmonteiro.acalculator.views.history.HistoryFragment
import cm.mmonteiro.acalculator.R
import cm.mmonteiro.acalculator.views.map.MapsFragment


class NavigationManager {
    companion object{
        private fun placeFragment(fm:FragmentManager,fragment: Fragment){
            val transition = fm.beginTransaction()
            transition.replace(R.id.frame,fragment)
            transition.addToBackStack(null)
            transition.commit()
        }
        fun gotCalculatorFragment(fm:FragmentManager){
            placeFragment(fm, CalculatorFragment())
        }
        fun gotHistoryFragment(fm:FragmentManager){
            placeFragment(fm, HistoryFragment())
        }

        fun getMapFragment(fm: FragmentManager) {
            placeFragment(fm, MapsFragment())
        }

    }
}