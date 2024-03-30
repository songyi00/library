package study.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Component
@Aspect
class AopAspect {

    @Pointcut("execution(* study.aop.AopService.aop2(..))")
    fun pointcut() {
    }

    @Around("pointcut()")
    fun run(joinPoint: ProceedingJoinPoint): Any? {

        println("aop start")
        try {
            return joinPoint.proceed()
        } finally {
            println("aop end")
        }
    }
}