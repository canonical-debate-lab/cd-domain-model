package com.canonicaldebatelab.infrastructure

import com.canonicaldebatelab.domain.DeclarativeSentence
import kotlin.test.*


/**
 * Tests for serialization of [DeclarativeSentence].
 */
class DeclarativeSentenceSerializationTest
{
    @Test
    fun can_serialize_and_deserialize_using_JSON()
    {
        val json = createDefaultJSON()
        val serializer = DeclarativeSentence.serializer()

        val sentence = DeclarativeSentence( "Socrates is a man." )
        val serialized = json.encodeToString( serializer, sentence )
        val parsed = json.decodeFromString( serializer, serialized )

        assertEquals( sentence, parsed )
    }

    @Test
    fun serialized_as_string_primitive()
    {
        val json = createDefaultJSON()
        val serializer = DeclarativeSentence.serializer()

        val text = "Kotlin is awesome."
        val sentence = DeclarativeSentence( text )
        val serialized = json.encodeToString( serializer, sentence )

        assertEquals( "\"$text\"", serialized )
    }
}
