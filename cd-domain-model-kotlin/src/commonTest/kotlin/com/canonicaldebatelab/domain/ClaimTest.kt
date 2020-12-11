package com.canonicaldebatelab.domain

import kotlin.test.*


/**
 * Tests for [Claim].
 */
class ClaimTest
{
    @Test
    fun fromStatement_succeeds()
    {
        val statement = Statement.fromDeclarativeSentence( "0", "Socrates is a man." )
        val origin = StatementOrigin.User( "Steven" )
        val claim = Claim.fromStatement( origin, statement, true )

        val expectedClaim = Claim( origin, statementId = "0", true )
        assertEquals( expectedClaim, claim )
    }

    @Test
    fun claims_builder_succeeds()
    {
        val statement = Statement.fromDeclarativeSentence( "0", "Socrates is a man." )
        val steven = StatementOrigin.User( "Steven" )

        val claimTrue = steven.claims( statement ).isTrue()
        assertEquals( Claim( steven, statement.id, true ), claimTrue )

        val claimFalse = steven.claims( statement ).isFalse()
        assertEquals( Claim( steven, statement.id, false ), claimFalse )
    }
}
