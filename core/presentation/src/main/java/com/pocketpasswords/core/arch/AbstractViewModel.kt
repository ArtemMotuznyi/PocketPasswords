package com.pocketpasswords.core.arch

import androidx.annotation.MainThread
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import androidx.lifecycle.ViewModel as AndroidViewModel


abstract class AbstractViewModel<VI : ViewIntent, VS : ViewState, VE : ViewEvent> :
    ViewModel<VI, VS, VE>, AndroidViewModel() {

    private val _viewEvents = Channel<VE>(Channel.UNLIMITED)
    private val _viewIntents = MutableSharedFlow<VI>(extraBufferCapacity = Int.MAX_VALUE)

    override val viewEvents: Flow<VE> = _viewEvents.receiveAsFlow()

    @MainThread
    override suspend fun processIntent(intent: VI) {
        _viewIntents.tryEmit(intent) //TODO add log
    }


}