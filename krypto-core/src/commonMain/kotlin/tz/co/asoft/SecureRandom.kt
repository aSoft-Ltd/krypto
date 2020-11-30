@file:Suppress("PackageDirectoryMismatch")

package tz.co.asoft

import tz.co.asoft.internal.arraycopy
import kotlin.random.Random

expect fun fillRandomBytes(array: ByteArray)
var randomUnittesting = false

open class SecureRandom : Random() {
    companion object : SecureRandom()

    private val temp = ByteArray(4)
    private fun getInt(): Int {
        fillRandomBytes(temp)
        val a = temp[0].toInt() and 0xFF
        val b = temp[1].toInt() and 0xFF
        val c = temp[2].toInt() and 0xFF
        val d = temp[3].toInt() and 0xFF
        return (a shl 24) or (b shl 16) or (c shl 8) or (d shl 0)
    }

    override fun nextBytes(array: ByteArray, fromIndex: Int, toIndex: Int): ByteArray {
        val random = ByteArray(toIndex - fromIndex)
        fillRandomBytes(random)
        arraycopy(random, 0, array, fromIndex, random.size)
        return array
    }

    override fun nextBits(bitCount: Int): Int {
        return getInt() and ((1 shl bitCount) - 1)
    }
}
