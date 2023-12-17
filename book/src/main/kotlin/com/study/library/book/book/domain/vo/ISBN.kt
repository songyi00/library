package com.study.library.book.book.domain.vo

class ISBN(
    val value: String
) {
    init {
        require(value.length == 13) { "올바르지 않은 isbn 형식입니다" }
    }
}
