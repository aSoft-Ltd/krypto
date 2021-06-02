@file:UseSerializers(LongAsStringSerializer::class)

package krypto

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlin.time.ExperimentalTime
import kotlin.time.days

@Serializable
data class SecurityKey @OptIn(ExperimentalTime::class) constructor(
    override var uid: String? = null,
    override val created: Long = Clock.System.now().toEpochMilliseconds(),
    override val expires: Long = (Clock.System.now() + 30.0.days).toEpochMilliseconds(),
    val value: String
) : KeyInfo {
    init {
        require(expires > created) { "Make sure expiration of keys happens after creation" }
    }
}