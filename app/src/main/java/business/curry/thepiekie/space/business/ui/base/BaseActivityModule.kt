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

package business.curry.thepiekie.space.business.ui.base

import androidx.lifecycle.ViewModel
import business.curry.thepiekie.space.business.di.base.PerFragment
import business.curry.thepiekie.space.business.di.base.ViewModelKey
import business.curry.thepiekie.space.business.ui.detail.OrderDetailFragment
import business.curry.thepiekie.space.business.ui.detail.OrderDetailViewModel
import business.curry.thepiekie.space.business.ui.orders.OrdersFragment
import business.curry.thepiekie.space.business.ui.orders.OrdersViewModel
import business.curry.thepiekie.space.business.ui.qr.QrScannerFragment
import business.curry.thepiekie.space.business.ui.qr.QrScannerViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class BaseActivityModule {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun contributeOrdersFragment(): OrdersFragment

    @Binds
    @IntoMap
    @ViewModelKey(OrdersViewModel::class)
    abstract fun bindFeedViewModel(viewModel: OrdersViewModel): ViewModel

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun contributeQrScannerFragment(): QrScannerFragment

    @Binds
    @IntoMap
    @ViewModelKey(QrScannerViewModel::class)
    abstract fun bindQrScannerViewModel(viewModel: QrScannerViewModel): ViewModel

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun contributeOrderDetailFragment(): OrderDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(OrderDetailViewModel::class)
    abstract fun bindOrderDetailViewModel(viewModel: OrderDetailViewModel): ViewModel

}