package com.study.library.book

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(
    basePackages = ["com.study.library.common"]
)
class BookApplication

fun main(args: Array<String>) {
    runApplication<BookApplication>(*args)
}
