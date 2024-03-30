package study.tracing

import com.linecorp.armeria.common.util.SafeCloseable
import com.linecorp.armeria.server.ServiceRequestContext
import kotlinx.coroutines.ThreadContextElement
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

class ArmeriaRequestCoroutineContext2(
    private val requestContext: ServiceRequestContext
) : AbstractCoroutineContextElement(ArmeriaRequestCoroutineContext2), ThreadContextElement<SafeCloseable?> {

    // restoreThreadContext 함수 구현
    override fun restoreThreadContext(context: CoroutineContext, oldState: SafeCloseable?) {
        oldState?.close()
    }

    // updateThreadContext 함수 구현
    override fun updateThreadContext(context: CoroutineContext): SafeCloseable {
        return requestContext.push()
    }

    companion object Key : CoroutineContext.Key<ArmeriaRequestCoroutineContext2>
}