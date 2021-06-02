package krypto

import krypto.encoding.Base64
import java.security.Key

fun Key.toBase64(): String = Base64.encode(encoded)