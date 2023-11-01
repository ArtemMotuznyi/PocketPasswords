package com.pocketpasswords.core.arch

import androidx.annotation.MainThread
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Object that will subscribes to a MviView's [ViewIntent]s,
 * process it and emit a [ViewState] back.
 *
 * @param VI Top class of the [ViewIntent] that the [ViewModel] will be subscribing to.
 * @param VS Top class of the [ViewState] the [ViewModel] will be emitting.
 * @param VE Top class of the [ViewEvent] that the [ViewModel] will be emitting.
 */
interface ViewModel<VI : ViewIntent, VS : ViewState, VE : ViewEvent> {
    val viewState: StateFlow<VS>

    val viewEvents: Flow<VE>

    /**
     * Must be called in [kotlinx.coroutines.Dispatchers.Main.immediate],
     * otherwise it will throw an exception.
     *
     * If you want to process an intent from other [kotlinx.coroutines.CoroutineDispatcher],
     * use `withContext(Dispatchers.Main.immediate) { processIntent(intent) }`.
     */
    @MainThread
    suspend fun processIntent(intent: VI)
}