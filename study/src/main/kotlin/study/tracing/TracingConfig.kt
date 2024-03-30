package study.tracing

import brave.Tracing
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TracingConfig {

    @Bean
    fun tracing(): Tracing {
        return Tracing.newBuilder().localServiceName("study").build()
    }
}