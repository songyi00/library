package nexters.armeria

import kotlinx.coroutines.*
import kotlinx.coroutines.slf4j.MDCContext

suspend fun threadContextElement() {
    val threadLocal = ThreadLocal.withInitial { "Default" }
    println("1" + Thread.currentThread())
    withContext(Dispatchers.IO + threadLocal.asContextElement()) {
        println("2" + Thread.currentThread())

        threadLocal.set("hihi")
        println(threadLocal.get()) // "hihi" 출력
    }
    println("3" + Thread.currentThread())
    println(threadLocal.get()) // "hihi" 출력
}

suspend fun singleThread() {
    val dispatcher = Dispatchers.IO
        .limitedParallelism(1)

    var s = 1000
    val def1 = List(100) {
        CoroutineScope(dispatcher).async {
            s -= 1
        }
    }
    val def2 = List(100) {
        CoroutineScope(dispatcher).async {
            s -= 1
        }
    }

    def2.awaitAll()
    def1.awaitAll()
    println(s)
}