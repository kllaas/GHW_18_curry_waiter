package business.curry.thepiekie.space.business.ui.qr

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import business.curry.thepiekie.space.business.R
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.databinding.OrdersFragmentBinding
import business.curry.thepiekie.space.business.databinding.QrScannerFragmentBinding
import business.curry.thepiekie.space.business.di.base.ViewModelFactory
import business.curry.thepiekie.space.business.ui.orders.OrdersFragment
import business.curry.thepiekie.space.business.ui.orders.OrdersViewModel
import business.curry.thepiekie.space.business.ui.orders.adapter.OrderAdapter
import com.budiyev.android.codescanner.*
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.orders_fragment.*
import kotlinx.android.synthetic.main.qr_scanner_fragment.*
import java.util.jar.Manifest
import javax.inject.Inject

class QrScannerFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewDataBinding: QrScannerFragmentBinding
    private lateinit var viewModel: QrScannerViewModel

    private var codeScanner: CodeScanner? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(QrScannerViewModel::class.java)

        viewDataBinding = QrScannerFragmentBinding.inflate(inflater, container, false).apply {
            setLifecycleOwner(this@QrScannerFragment)
            viewModel = this@QrScannerFragment.viewModel
        }

        viewModel.orderPlace.observe(this, Observer {
            openDetailedOrder(it)
        })

        return viewDataBinding.root
    }

    private fun openDetailedOrder(it: OrderPlace?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    //___________permission part start______________


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPermissions()
    }

    private fun setupPermissions() {
        val permission = checkSelfPermission(
            context!!,
            android.Manifest.permission.CAMERA
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        } else {
            setUpScanner()
        }
    }

    private fun makeRequest() {
        requestPermissions(
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    activity!!.onBackPressed()
                } else {
                    setUpScanner()
                }
            }
        }
    }


    //___________permission part end______________


    //___________scanner part start______________


    private fun setUpScanner() {
        codeScanner = CodeScanner(context!!, scanner_view)


        codeScanner!!.decodeCallback = DecodeCallback {
            activity!!.runOnUiThread {
                viewModel.onQrScanned(it)
            }
        }

        codeScanner!!.errorCallback = ErrorCallback {
            activity!!.runOnUiThread {
                Toast.makeText(activity, "Camera initialization error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }

        codeScanner!!.startPreview()
    }

    override fun onResume() {
        super.onResume()

        if (codeScanner != null)
            codeScanner!!.startPreview()
    }

    override fun onPause() {
        if (codeScanner != null)
            codeScanner!!.releaseResources()

        super.onPause()
    }

    //___________scanner part end______________

    companion object {
        fun newInstance(): QrScannerFragment = QrScannerFragment()

        const val CAMERA_REQUEST_CODE = 42
    }

}