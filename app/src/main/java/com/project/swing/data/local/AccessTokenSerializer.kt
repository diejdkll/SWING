package com.project.swing.data.local

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.project.swing.AccessToken
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class AccessTokenSerializer @Inject constructor() : Serializer<AccessToken> {
    override val defaultValue: AccessToken = AccessToken.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AccessToken =
        try {
            AccessToken.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", e)
        }

    override suspend fun writeTo(t: AccessToken, output: OutputStream) {
        t.writeTo(output)
    }
}