@file:UseSerializers(LongAsStringSerializer::class)

package krypto

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlin.time.ExperimentalTime
import kotlin.time.days

@Serializable
data class SecurityKeyPair @OptIn(ExperimentalTime::class) constructor(
    override var uid: String? = null,
    override val created: Long = Clock.System.now().toEpochMilliseconds(),
    override val expires: Long = (Clock.System.now() + 30.0.days).toEpochMilliseconds(),
    val privateKey: String,
    val publicKey: String
) : KeyInfo {
    init {
        require(expires > created) { "Make sure expiration of keys happens after creation" }
    }

    val privateSecurityKey get() = SecurityKey(uid, created, expires, privateKey)
    val publicSecurityKey get() = SecurityKey(uid, created, expires, publicKey)
}