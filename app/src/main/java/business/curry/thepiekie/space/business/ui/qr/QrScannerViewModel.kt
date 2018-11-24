package business.curry.thepiekie.space.business.ui.qr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import business.curry.thepiekie.space.business.data.model.OrderPlace
import business.curry.thepiekie.space.business.domain.orders.*
import com.example.reddit.shared.domain.UseCaseResult
import javax.inject.Inject

class QrScannerViewModel @Inject constructor(
    private var loadOrderByIdUseCase: LoadOrderByIdUseCase
) : ViewModel() {

    private val loadOrdersUseCaseResult: MediatorLiveData<UseCaseResult<LoadPostByIdResult>> =
        loadOrderByIdUseCase.observe()

    private var _orderPlace = MediatorLiveData<OrderPlace>()
    val orderPlace: LiveData<OrderPlace>
        get() = _orderPlace

    private val _getOrderInProgress = MediatorLiveData<Boolean>()
    fun getOrderInProgress(): LiveData<Boolean> = _getOrderInProgress

    fun onQrScanned(result: com.google.zxing.Result) {
        _getOrderInProgress.value = true
        loadOrderByIdUseCase.execute(result.text)
            .subscribe(
                {
                    _getOrderInProgress.value = false
                    _orderPlace.value = it
                }, {
                    _getOrderInProgress.value = false
                }
            )
    }
}
