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

package business.curry.thepiekie.space.business.domain.login

import android.content.Context
import business.curry.thepiekie.space.business.data.local.PrefsDataStore
import business.curry.thepiekie.space.business.data.model.LoginResponse
import business.curry.thepiekie.space.business.data.remote.CurryApi
import business.curry.thepiekie.space.business.domain.MediatorUseCase
import com.example.reddit.shared.domain.UseCaseResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val curryApi: CurryApi,
    private val prefsDataStore: PrefsDataStore,
    private val context: Context
) : MediatorUseCase<LoginParams, LoginResult>() {

    override fun execute(parameters: LoginParams) {
        disposables.add(curryApi.login(parameters)
            .doOnNext {
                prefsDataStore.saveLoginResponse(it, context)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    result.postValue(UseCaseResult.Success(LoginResult(it)))
                }, {
                    result.postValue(UseCaseResult.Error(Exception("Login error: ${it.message}")))
                }
            )
        )
    }

}

data class LoginParams(val email: String?, val password: String?)
data class LoginResult(val orderPlace: LoginResponse)