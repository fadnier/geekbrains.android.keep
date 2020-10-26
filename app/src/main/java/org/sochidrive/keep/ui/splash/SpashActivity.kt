package org.sochidrive.keep.ui.splash

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import org.sochidrive.keep.ui.base.BaseActivity
import org.sochidrive.keep.ui.main.MainActivity

class SpashActivity: BaseActivity<Boolean?, SplashViewState>() {

    companion object {
        fun start(context: Context) = Intent(context, SpashActivity::class.java).apply { context.startActivity(this) }
    }

    override val layoutRes = null
    override val viewModel by lazy { ViewModelProvider(this).get(SplashViewModel::class.java) }
    override fun renderData(data: Boolean?) {
        data?.takeIf { it }?.let { startMainActivity() }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestUser()
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }

}