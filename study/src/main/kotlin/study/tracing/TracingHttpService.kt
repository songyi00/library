package study.tracing

import com.linecorp.armeria.server.annotation.Get
import org.springframework.stereotype.Controller

@Controller
class TracingHttpService(private val tracingService: TracingService) {

    @Get("/trace2")
    suspend fun tracing() {
        tracingService.trace()
    }
}