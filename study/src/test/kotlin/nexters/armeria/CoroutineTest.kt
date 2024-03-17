package nexters.armeria

import io.kotest.core.spec.style.FunSpec
import kotlinx.coroutines.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTest : FunSpec({

    test("coroutine 비동기 테스트") {
        println(Thread.currentThread())
        runTest(StandardTestDispatcher()) {
            println(Thread.currentThread())
            launch1(this.coroutineContext)
        }
        println("test end")
    }

})

/*
 * 코루틴 비동기 테스트의 경우 runTest 를 사용할 경우 TestDispatcher 내에서 실행된다.
 * StandardTestDispatcher 를 디폴트로 사용하며 같은 컨텍스트, 즉 같은 작업 트리 내에 있어야 자식 코루틴의 완료를 부모 코루틴이 대기한다.
 * 만약 컨텍스트가 달라질 경우 runTest 내에서 관리할 수 없으며 비동기로 실행되어 내부 Job 완료 시점을 관리할 수 없다
 * 부모-자식 코루틴 관계가 되려면 컨텍스트가 공유되어야 한다.
 *
 */
fun launch1(coroutineContext: CoroutineContext) {
    println("어떤 컨텍스트?")
    println(coroutineContext)
    println(Thread.currentThread())
    CoroutineScope(coroutineContext + Dispatchers.Default).launch {
        delay(5000)
        println("launch1")
        println(Thread.currentThread())
    }
}