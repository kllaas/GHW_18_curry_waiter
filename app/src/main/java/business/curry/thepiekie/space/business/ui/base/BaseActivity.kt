package business.curry.thepiekie.space.business.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import business.curry.thepiekie.space.business.R
import business.curry.thepiekie.space.business.ui.orders.OrdersFragment
import dagger.android.support.DaggerAppCompatActivity

class BaseActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.base_container, OrdersFragment.newInstance(), OrdersFragment.javaClass.simpleName)
                .commit()
        }
    }
}
