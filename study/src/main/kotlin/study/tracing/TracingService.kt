package study.tracing

import brave.Tracing
import brave.propagation.CurrentTraceContext
import brave.propagation.ThreadLocalSpan
import brave.propagation.TraceIdContext
import com.linecorp.armeria.common.RequestContext
import com.linecorp.armeria.common.brave.RequestContextCurrentTraceContext
import com.linecorp.armeria.common.kotlin.ArmeriaRequestCoroutineContext
import com.linecorp.armeria.common.kotlin.asCoroutineContext
import com.linecorp.armeria.server.ServiceRequestContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.slf4j.MDCContext
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.imageio.spi.ServiceRegistry
import kotlin.math.log

@Service
class TracingService(private val tracing: Tracing) {

    private val logger = LoggerFactory.getLogger(javaClass)

    /**
     * armeria 로 관리되는 서비스에 대해서만 RequestContext 가 생성된다.
     * 코루틴의 withContext 를 사용할 경우 현재 context를 복사하기 때문에 RequestContext 가 전파된다.
     * CouroutineScope에 context를 복사하려면 CoroutineScope(Dispatchers.IO + rctx).launch {..} 이런식으로 컨텍스트를 추가해주어야함
     * tracing 은 코루틴 컨텍스트가 바뀔 때 함께 전파된다.
     * armeria request context id 와 armeria tracing id 는 다르다.
     */
    suspend fun trace() {
        val rctx = ArmeriaRequestCoroutineContext2(ServiceRequestContext.current())
        logger.info("request context: " + ServiceRequestContext.current().id().text())
        withSpan(tracing, "hi", "hi") {
            logger.info("request context id: " + ServiceRequestContext.current().id().text())
            logger.info("trace id: " + Tracing.current().currentTraceContext().get().traceIdString())
            logger.info("======")
            withContext(Dispatchers.IO) {
                logger.info("withContext(Dispatchers.IO)")
                logger.info("request context id: " + ServiceRequestContext.current().id().text())
                logger.info("trace id: " + Tracing.current().currentTraceContext().get().traceIdString())
            }
            logger.info("======")

            CoroutineScope(Dispatchers.IO + rctx).launch {
                logger.info("CoroutineScope(Dispatchers.IO)")
                logger.info("trace id: " + Tracing.current().currentTraceContext().get().traceIdString())
                logger.info("request context id: " + ServiceRequestContext.current().id().text())
            }
            logger.info("trace id: " + Tracing.current().currentTraceContext().get().traceIdString())
        }
    }
}