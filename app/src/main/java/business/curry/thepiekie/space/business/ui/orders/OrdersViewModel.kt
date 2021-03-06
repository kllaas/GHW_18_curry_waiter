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

package business.curry.thepiekie.space.business.ui.orders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.domain.orders.LoadOrdersUseCaseResult
import business.curry.thepiekie.space.business.domain.orders.LoadOrdersUseCase
import business.curry.thepiekie.space.business.ui.login.OnLoginClick
import com.example.reddit.shared.domain.UseCaseResult
import javax.inject.Inject

class OrdersViewModel @Inject constructor(
  private val loadOrdersUseCase: LoadOrdersUseCase
) : ViewModel(), OnScanOrderClickClickListener {

    private val loadOrdersUseCaseResult: MediatorLiveData<UseCaseResult<LoadOrdersUseCaseResult>> = loadOrdersUseCase.observe()

    private val _orders = MediatorLiveData<List<OrderPlace>>()
    val orders: LiveData<List<OrderPlace>>
        get() = _orders

    private val _navigateToQrScanner = MediatorLiveData<Unit>()
    val navigateToQrScanner: LiveData<Unit>
        get() = _navigateToQrScanner

    init {
        _orders.addSource(loadOrdersUseCaseResult) {
            (loadOrdersUseCaseResult.value as? UseCaseResult.Success)?.data?.posts?.let {
                _orders.value = it
            }
        }

        loadOrdersUseCase.execute()
    }

    override fun onScanOrderClick() {
        _navigateToQrScanner.value = Unit
    }

}

interface OnScanOrderClickClickListener {
    fun onScanOrderClick()
}