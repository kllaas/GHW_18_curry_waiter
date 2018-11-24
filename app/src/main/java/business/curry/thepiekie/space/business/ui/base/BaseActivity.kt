package business.curry.thepiekie.space.business.ui.base

import android.os.Bundle
import business.curry.thepiekie.space.business.R
import business.curry.thepiekie.space.business.data.local.PrefsDataStore
import business.curry.thepiekie.space.business.ui.login.LoginFragment
import business.curry.thepiekie.space.business.ui.orders.OrdersFragment
import business.curry.thepiekie.space.business.util.ext.inTransaction
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var prefsDataStore: PrefsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val loggedIn = prefsDataStore.loggedIn(this)
        if (loggedIn) {
            if (savedInstanceState == null) {
                supportFragmentManager.inTransaction {
                    add(R.id.base_container, OrdersFragment.newInstance(), OrdersFragment.javaClass.simpleName)
                }
            }
        } else {
            if (savedInstanceState == null) {
                supportFragmentManager.inTransaction {
                    add(R.id.base_container, LoginFragment.newInstance(), LoginFragment.javaClass.simpleName)
                }
            }
        }
    }
}
