package com.canonicaldebatelab.domain

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


/**
 * As defined in linguistics:
 * a [sentence] which makes a statement, contrasted with interrogative, exclamatory, and imperative sentences.
 */
@Serializable( DeclarativeSentenceSerializer::class )
data class DeclarativeSentence( val sentence: String )


object DeclarativeSentenceSerializer : KSerializer<DeclarativeSentence>
{
     override val descriptor: SerialDescriptor =
         PrimitiveSerialDescriptor( "DeclarativeSentence", PrimitiveKind.STRING )

    override fun serialize( encoder: Encoder, value: DeclarativeSentence ) = encoder.encodeString( value.sentence )
    override fun deserialize( decoder: Decoder ): DeclarativeSentence = DeclarativeSentence( decoder.decodeString() )
}
