package business.curry.thepiekie.space.business.ui.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import business.curry.thepiekie.space.business.R
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.databinding.OrdersFragmentBinding
import business.curry.thepiekie.space.business.di.base.ViewModelFactory
import business.curry.thepiekie.space.business.ui.orders.adapter.OrderAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.orders_fragment.*
import java.security.acl.Owner
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

        viewModel.orders.observe(this, Observer {
            val adapter = OrderAdapter(it)
            order_list.adapter = adapter
        })

        return viewDataBinding.root
    }

    companion object {
        fun newInstance(): OrdersFragment = OrdersFragment()
    }
}