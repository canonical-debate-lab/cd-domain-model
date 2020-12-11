package com.canonicaldebatelab.infrastructure

import com.canonicaldebatelab.domain.StatementOrigin
import kotlin.test.*


/**
 * Tests for serialization of [StatementOrigin].
 */
class StatementOriginSerializationTest
{
    @Test
    fun can_serialize_and_deserialize_claim()
    {
        val json = createDefaultJSON()
        val serializer = StatementOrigin.serializer()

        val user = StatementOrigin.User( "Steven" )
        val userSerialized = json.encodeToString( serializer, user )
        val userParsed = json.decodeFromString( serializer, userSerialized )
        assertEquals( user, userParsed )

        val source = StatementOrigin.Source( "http://google.com" )
        val sourceSerialized = json.encodeToString( serializer, source )
        val sourceParsed = json.decodeFromString( serializer, sourceSerialized )
        assertEquals( source, sourceParsed )
    }
}
