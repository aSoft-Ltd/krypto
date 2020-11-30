package tz.co.asoft

import kotlin.test.Test
import kotlin.test.assertEquals

class SHA1Test {
    @Test
    fun name() {
        assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", SHA1.digest("".toByteArray()).hex)
        assertEquals("86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", SHA1.digest("a".toByteArray()).hex)
        assertEquals(
            "32d10c7b8cf96570ca04ce37f2a19d84240d3a89",
            SHA1.digest("abcdefghijklmnopqrstuvwxyz".toByteArray()).hex
        )

        assertEquals("86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", "a".toByteArray().hash(
            SHA1
        ).hex)
        assertEquals("86f7e437faa5a7fce15d1ddcb9eaeaea377667b8", "a".toByteArray().sha1().hex)
    }
}