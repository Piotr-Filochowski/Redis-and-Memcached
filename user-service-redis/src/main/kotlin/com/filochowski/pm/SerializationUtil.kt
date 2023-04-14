package com.filochowski.pm

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter


object LocalDateSerializer : KSerializer<LocalDate> {


    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDate {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(decoder.decodeString(), formatter)
        return date
    }

    override fun serialize(encoder: Encoder, value: LocalDate) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        encoder.encodeString(formatter.format(value))

    }
}