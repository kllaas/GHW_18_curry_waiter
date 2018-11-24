package business.curry.thepiekie.space.business.ui.orders

import androidx.lifecycle.ViewModel
import business.curry.thepiekie.space.business.di.base.PerFragment
import business.curry.thepiekie.space.business.di.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
internal abstract class OrdersModule {

    @PerFragment
    @ContributesAndroidInjector
    internal abstract fun contributeOrdersFragment(): OrdersFragment

    @Binds
    @IntoMap
    @ViewModelKey(OrdersViewModel::class)
    abstract fun bindOrdersViewModel(viewModel: OrdersViewModel): ViewModel

}