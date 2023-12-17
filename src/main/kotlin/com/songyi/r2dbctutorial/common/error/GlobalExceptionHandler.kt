package com.songyi.r2dbctutorial.common.error

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

import reactor.core.publisher.Mono

@Component
class GlobalExceptionHandler(
    errorAttributes: ErrorAttributes,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : AbstractErrorWebExceptionHandler(errorAttributes, WebProperties.Resources(), applicationContext) {
    init {
        super.setMessageWriters(serverCodecConfigurer.writers)
        super.setMessageReaders(serverCodecConfigurer.readers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            RequestPredicates.all()
        ) { request: ServerRequest -> renderErrorResponse(request) }
    }

    private fun renderErrorResponse(request: ServerRequest): Mono<ServerResponse> {
        return when (val e = getError(request)) {
            is IllegalArgumentException -> {
                ServerResponse.status(HttpStatusCode.valueOf(400))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ErrorResponse(HttpStatus.BAD_REQUEST, e.message ?: e.stackTraceToString()))
            }

            else -> {
                ServerResponse.status(HttpStatusCode.valueOf(500))
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: e.stackTraceToString()))
            }
        }
    }
}
