package business.curry.thepiekie.space.business.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import business.curry.thepiekie.space.business.R
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.databinding.OrdersFragmentBinding
import business.curry.thepiekie.space.business.di.base.ViewModelFactory
import business.curry.thepiekie.space.business.ui.detail.OrderDetailFragment
import business.curry.thepiekie.space.business.ui.orders.adapter.OrderAdapter
import business.curry.thepiekie.space.business.ui.qr.QrScannerFragment
import business.curry.thepiekie.space.business.util.ext.inTransaction
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.orders_fragment.*
import javax.inject.Inject

class OrdersFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewDataBinding: OrdersFragmentBinding
    private lateinit var viewModel: OrdersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(OrdersViewModel::class.java)

        viewDataBinding = OrdersFragmentBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@OrdersFragment)
            viewModel = this@OrdersFragment.viewModel
        }

        viewModel.navigateToQrScanner.observe(this, Observer {
            openQrScannerScreen()
        })


        viewModel.orders.observe(this, Observer {
            val adapter = OrderAdapter(it) { onOrderPlaceClick(it) }
            order_list.adapter = adapter
        })

        return viewDataBinding.root
    }

    private fun onOrderPlaceClick(orderPlace: OrderPlace) {
        activity!!.supportFragmentManager.inTransaction {
            add(R.id.base_container, OrderDetailFragment.newInstance(orderPlace))
                .addToBackStack(OrdersFragment.javaClass.simpleName)
        }
    }

    private fun openQrScannerScreen() {
        if (activity == null) return

        activity!!.supportFragmentManager.inTransaction {
            add(R.id.base_container, QrScannerFragment.newInstance())
                .addToBackStack(QrScannerFragment.javaClass.simpleName)
        }
    }

    companion object {
        fun newInstance(): OrdersFragment = OrdersFragment()
    }
}