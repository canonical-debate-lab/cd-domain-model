package com.canonicaldebatelab.infrastructure

import com.canonicaldebatelab.domain.Claim
import com.canonicaldebatelab.domain.StatementOrigin
import kotlin.test.*


/**
 * Tests for serialization of [Claim].
 */
class ClaimSerializationTest
{
    @Test
    fun can_serialize_and_deserialize_claim()
    {
        val json = createDefaultJSON()
        val serializer = Claim.serializer()

        val claim = Claim( StatementOrigin.User( "Steven" ), "0", true )
        val serialized = json.encodeToString( serializer, claim )
        val parsed = json.decodeFromString( serializer, serialized )

        assertEquals( claim, parsed )
    }
}
