package cm.mmonteiro.acalculator.remote

import androidx.lifecycle.ViewModel
import cm.mmonteiro.acalculator.domain.AuthLogic

const val ENDPOINT ="https://cm-calculadora.herokuapp.com/api/"
class LoginViewModel: ViewModel() {
    private val authLogic = AuthLogic(RetrofitBuilder.getInstance(ENDPOINT))
}