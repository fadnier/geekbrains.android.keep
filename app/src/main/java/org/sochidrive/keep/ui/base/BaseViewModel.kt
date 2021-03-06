package org.sochidrive.keep.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<S> : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default + Job()
    }

    private val viewStateChannel = BroadcastChannel<S>(Channel.CONFLATED)
    private val errorChannel = Channel<Throwable>()

    fun getViewState(): ReceiveChannel<S> = viewStateChannel.openSubscription()
    fun getErrorChannel(): ReceiveChannel<Throwable> = errorChannel

    protected fun setError(e: Throwable) = launch {
        errorChannel.send(e)
    }

    protected fun setData(data: S) = launch {
        viewStateChannel.send(data)
    }

    override fun onCleared() {
        super.onCleared()
        viewStateChannel.close()
        errorChannel.close()
        coroutineContext.cancel()
    }
}