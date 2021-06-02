package krypto

interface KeyInfo {
    var uid: String?
    val created: Long
    val expires: Long
}