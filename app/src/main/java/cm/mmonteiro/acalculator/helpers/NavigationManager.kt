package cm.mmonteiro.acalculator.helpers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import cm.mmonteiro.acalculator.fragments.CalculatorFragment
import cm.mmonteiro.acalculator.fragments.HistoryFragment
import cm.mmonteiro.acalculator.R


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

    }
}