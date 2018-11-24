/*
 * MIT License
 *
 * Copyright (c) 2018 Artem Vasylenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package business.curry.thepiekie.space.business.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import business.curry.thepiekie.space.business.data.model.LoginResponse
import business.curry.thepiekie.space.business.domain.login.LoginResult
import business.curry.thepiekie.space.business.domain.login.LoginUseCase
import business.curry.thepiekie.space.business.domain.orders.LoadOrdersUseCaseResult
import business.curry.thepiekie.space.business.domain.orders.LoadOrdersUseCase
import business.curry.thepiekie.space.business.ui.orders.OnScanOrderClickClickListener
import com.example.reddit.shared.domain.UseCaseResult
import javax.inject.Inject
import androidx.databinding.ObservableField
import business.curry.thepiekie.space.business.domain.login.LoginParams


class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel(), OnLoginClick {

    var username = ObservableField("")
    var password = ObservableField("")

    val loginResult: MediatorLiveData<UseCaseResult<LoginResult>> = loginUseCase.observe()

    private val _loginResponse = MediatorLiveData<LoginResult>()
    val loginResponse: LiveData<LoginResult>
        get() = _loginResponse

    private val _loginClick = MediatorLiveData<Unit>()
    val loginClick: LiveData<Unit>
        get() = _loginClick

    override fun onLoginClick() {
        _loginClick.value = Unit
        _loginResponse.addSource(loginResult) {}
        loginUseCase.execute(LoginParams(username.get(), password.get()))
    }

}

interface OnLoginClick {
    fun onLoginClick()
}