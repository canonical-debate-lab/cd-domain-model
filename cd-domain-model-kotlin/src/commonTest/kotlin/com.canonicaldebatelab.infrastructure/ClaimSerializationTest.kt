package com.canonicaldebatelab.infrastructure

import com.canonicaldebatelab.domain.ClaimSnapshot
import com.canonicaldebatelab.domain.DeclarativeSentence
import kotlin.test.*


/**
 * Tests for serialization of [ClaimSnapshot].
 */
class ClaimSerializationTest
{
    @Test
    fun can_serialize_and_deserialize_snapshot_using_JSON()
    {
        val json = createDefaultJSON()
        val serializer = ClaimSnapshot.serializer()

        val sentence = DeclarativeSentence( "Socrates is a man." )
        val snapshot = ClaimSnapshot( "0", setOf( sentence ) )
        val serialized = json.encodeToString( serializer, snapshot )
        val parsed = json.decodeFromString( serializer, serialized )

        assertEquals( snapshot, parsed )
    }
}
