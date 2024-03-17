package study.aop

import org.springframework.stereotype.Service

@Service
class AopService {

    fun aop1() {
        println("aop1")
    }

    suspend fun aop2() {
        println("aop2")
    }
}
