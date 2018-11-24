package business.curry.thepiekie.space.business

import android.util.Log
import business.curry.thepiekie.space.business.di.application.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler { e ->
            Log.e(
                "Undeliverable",
                "Undeliverable exception received, not sure what to do" + e.message
            )
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }
}