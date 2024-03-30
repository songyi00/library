package study

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StudyApplication

fun main(args: Array<String>) {
    runApplication<StudyApplication>(*args)
}

// TODO
// 1. Armeria Service RequestContext로 테스트
// 2. ThreadContextElement 로 직접 만들어서 테스트