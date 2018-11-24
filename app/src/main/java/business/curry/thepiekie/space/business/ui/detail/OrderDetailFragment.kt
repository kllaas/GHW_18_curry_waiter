package business.curry.thepiekie.space.business.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat.getExtras
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import business.curry.thepiekie.space.business.R
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.databinding.OrderDetailBinding
import business.curry.thepiekie.space.business.di.base.ViewModelFactory
import business.curry.thepiekie.space.business.ui.detail.adapter.DishAdapter
import business.curry.thepiekie.space.business.ui.orders.adapter.OrderAdapter
import business.curry.thepiekie.space.business.ui.qr.QrScannerFragment
import business.curry.thepiekie.space.business.util.ext.inTransaction
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.order_detail.*
import kotlinx.android.synthetic.main.orders_fragment.*
import javax.inject.Inject

class OrderDetailFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewDataBinding: OrderDetailBinding
    private lateinit var viewModel: OrderDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(OrderDetailViewModel::class.java)

        viewDataBinding = OrderDetailBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@OrderDetailFragment)
            viewModel = this@OrderDetailFragment.viewModel
        }

        viewModel.order.observe(this, Observer {
            val adapter = DishAdapter(it.orderDishes)
            dishes.adapter = adapter
        })

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getOrderFromArgs()
    }

    private fun getOrderFromArgs() {
        if (arguments == null) return

        val order = arguments!!.getParcelable(OrderPlace.javaClass.simpleName) as OrderPlace
        viewModel.setOrder(order)
    }

    companion object {
        fun newInstance(order: OrderPlace) : OrderDetailFragment {
            val fragment = OrderDetailFragment()
            val bundle = Bundle()

            bundle.putParcelable(OrderPlace.javaClass.simpleName, order)

            fragment.arguments = bundle

            return fragment
        }
    }
}