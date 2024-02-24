package com.study.library.core.auth.encrypt

import java.security.MessageDigest

private val SHA256: MessageDigest = MessageDigest.getInstance("SHA-256")

fun sha256Encrypt(plainText: String): String = bytesToHex(SHA256.digest(plainText.toByteArray()))

private fun bytesToHex(bytes: ByteArray): String {
    return buildString {
        for (b in bytes) {
            append(String.format("%02x", b))
        }
    }
}