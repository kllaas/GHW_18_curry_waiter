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
import com.example.reddit.shared.domain.UseCaseResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import javax.inject.Inject

/**
 * Loads orders and returns them wrapped in
 * [PagedList] for Paging support
 */
class LoadOrdersUseCase @Inject constructor(
    private val ordersDataSource: OrdersDataSource
) : MediatorUseCase<Unit?, LoadOrdersUseCaseResult>() {

    /**
     * Executes the use case with no parameters
     */
    fun execute() {
        this.execute(null)
    }

    override fun execute(parameters: Unit?) {
        disposables.add(ordersDataSource.loadOrders()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.value = UseCaseResult.Success(LoadOrdersUseCaseResult(it))
            }, {
                result.value = UseCaseResult.Error(Exception(it))
            })
        )
    }
}

/**
 * Class that represents result of [LoadOrdersUseCase]
 */
data class LoadOrdersUseCaseResult(val posts: List<OrderPlace>)