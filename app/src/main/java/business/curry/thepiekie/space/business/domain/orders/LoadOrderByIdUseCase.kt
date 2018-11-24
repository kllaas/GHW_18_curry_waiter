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

package business.curry.thepiekie.space.business.domain.orders

import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.data.remote.OrdersDataSource
import business.curry.thepiekie.space.business.domain.MediatorUseCase
import business.curry.thepiekie.space.business.internal.DefaultScheduler
import com.example.reddit.shared.domain.UseCaseResult
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Load post by it's id from the repository
 * Notice that now only cache is in [PostsRepository]
 */
class LoadOrderByIdUseCase @Inject constructor(
    private val ordersDataSource: OrdersDataSource
) : MediatorUseCase<LoadPostsByIdParameters, LoadPostByIdResult>() {

    override fun execute(parameters: LoadPostsByIdParameters) {
        disposables.add(ordersDataSource.loadOrderById("someParam")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    result.postValue(UseCaseResult.Success(LoadPostByIdResult(it)))
                }, {
                    result.postValue(UseCaseResult.Error(Exception("There is no such order, sorry.")))
                }
            )
        )
    }

    fun execute(id: String): Observable<OrderPlace> {
        return ordersDataSource.loadOrderById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

data class LoadPostsByIdParameters(val orderId: String)
data class LoadPostByIdResult(val orderPlace: OrderPlace)