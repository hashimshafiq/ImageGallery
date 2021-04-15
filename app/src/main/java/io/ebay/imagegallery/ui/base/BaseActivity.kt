package io.ebay.imagegallery.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import io.ebay.imagegallery.ImageGalleryApp
import io.ebay.imagegallery.di.component.ActivityComponent
import io.ebay.imagegallery.di.component.DaggerActivityComponent
import io.ebay.imagegallery.di.module.ActivityModule
import io.ebay.imagegallery.utils.common.Toaster
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel : VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutView())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, {
            it.data?.run { showMessage(this) }
        })
    }

    open fun showMessage(message: String) = Toaster.show(applicationContext, message)

    open fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }
        else super.onBackPressed()
    }

    private fun buildActivityComponent() =
            DaggerActivityComponent
                    .builder()
                    .applicationComponent((application as ImageGalleryApp).applicationComponent)
                    .activityModule(ActivityModule(this))
                    .build()



    protected abstract fun provideLayoutView(): View

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)

}