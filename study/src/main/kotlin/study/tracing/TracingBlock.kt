package study.tracing

import brave.Tracing

suspend fun <T> withSpan(
    tracing: Tracing,
    spanName: String,
    message: String = "",
    block: suspend () -> T
): T {
    val tracer = tracing.tracer()
    val newSpan = tracer.nextSpan().name(spanName).start()

    return try {
        tracer.withSpanInScope(newSpan).use {
            println("span start")
            newSpan.tag("message", message)
            block()
        }
    } finally {
        newSpan.finish()
        println("span end")
    }
}