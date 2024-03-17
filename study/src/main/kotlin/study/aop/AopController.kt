package study.aop

import kotlinx.coroutines.reactor.mono
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class AopController(
    private val aopService: AopService
) {

    @GetMapping("/aop1")
    fun startAop() {
        aopService.aop1()
    }

    @GetMapping("/aop2")
    suspend fun startAop2() {
        mono {
            aopService.aop2()
        }.subscribe()
    }
}