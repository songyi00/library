package com.study.library

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ConfigurationPropertiesScan
@SpringBootApplication
@ComponentScan(basePackages = ["com.study.library.common"])
class AccountApplication

fun main(args: Array<String>) {
    runApplication<AccountApplication>(*args)
}
