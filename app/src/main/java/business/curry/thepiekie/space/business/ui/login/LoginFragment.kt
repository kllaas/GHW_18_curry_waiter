package business.curry.thepiekie.space.business.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import business.curry.thepiekie.space.business.R
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.databinding.LoginBinding
import business.curry.thepiekie.space.business.di.base.ViewModelFactory
import business.curry.thepiekie.space.business.domain.Result
import business.curry.thepiekie.space.business.ui.detail.OrderDetailFragment
import business.curry.thepiekie.space.business.ui.orders.OrdersFragment
import business.curry.thepiekie.space.business.ui.orders.adapter.OrderAdapter
import business.curry.thepiekie.space.business.ui.qr.QrScannerFragment
import business.curry.thepiekie.space.business.util.ext.inTransaction
import com.example.reddit.shared.domain.UseCaseResult
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.orders_fragment.*
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewDataBinding: LoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(LoginViewModel::class.java)

        viewDataBinding = LoginBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@LoginFragment)
            viewModel = this@LoginFragment.viewModel
        }

        viewModel.loginResult.observe(this, Observer {
            when (it) {
                is UseCaseResult.Success -> {
                    openNextScreen()
                }
                is UseCaseResult.Error -> {

                }
            }
        })

        return viewDataBinding.root
    }

    private fun openNextScreen() {
        activity!!.supportFragmentManager.inTransaction {
            replace(R.id.base_container, OrdersFragment.newInstance(), OrdersFragment.javaClass.simpleName)
        }
    }

    companion object {
        fun newInstance(): LoginFragment = LoginFragment()
    }
}