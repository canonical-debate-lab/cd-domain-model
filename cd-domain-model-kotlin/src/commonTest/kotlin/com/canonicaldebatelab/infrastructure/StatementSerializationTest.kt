package com.canonicaldebatelab.infrastructure

import com.canonicaldebatelab.domain.StatementSnapshot
import com.canonicaldebatelab.domain.DeclarativeSentence
import kotlin.test.*


/**
 * Tests for serialization of [StatementSnapshot].
 */
class StatementSerializationTest
{
    @Test
    fun can_serialize_and_deserialize_snapshot_using_JSON()
    {
        val json = createDefaultJSON()
        val serializer = StatementSnapshot.serializer()

        val sentence = DeclarativeSentence( "Socrates is a man." )
        val snapshot = StatementSnapshot( "0", setOf( sentence ) )
        val serialized = json.encodeToString( serializer, snapshot )
        val parsed = json.decodeFromString( serializer, serialized )

        assertEquals( snapshot, parsed )
    }
}
