package study.tracing

import com.linecorp.armeria.server.ServerBuilder
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.logging.AccessLogWriter
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class ArmeriaConfiguration {
    @Bean
    fun armeriaServerConfigurator(tracingHttpService: TracingHttpService): ArmeriaServerConfigurator {

        return ArmeriaServerConfigurator { builder: ServerBuilder ->
            builder.serviceUnder("/docs", DocService())
            builder.annotatedService(tracingHttpService)
            builder.decorator(LoggingService.newDecorator())
        }
    }
}