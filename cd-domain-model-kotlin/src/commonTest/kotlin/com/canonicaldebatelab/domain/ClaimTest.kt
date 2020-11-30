package com.canonicaldebatelab.domain

import kotlin.test.*


/**
 * Tests for [Claim].
 */
class ClaimTest
{
    @Test
    fun fromDeclarativeSentence_succeeds()
    {
        val id = "0"
        val sentence = "Socrates is a man."
        val claim = Claim.fromDeclarativeSentence( id, "Socrates is a man." )

        assertEquals( id, claim.id )
        assertEquals( 1, claim.sentences.size )
        assertEquals( DeclarativeSentence( sentence ), claim.sentences.single() )
    }

    @Test
    fun addDeclarativeSentence_succeeds()
    {
        val claim = Claim.fromDeclarativeSentence( "0", "Socrates is a man." )

        val additionalSentence = "Socrates, the Greek philosopher, is a man."
        claim.addDeclarativeSentence( additionalSentence )

        assertEquals( 2, claim.sentences.size )
        assertTrue( DeclarativeSentence( additionalSentence ) in claim.sentences )
    }

    @Test
    fun addDeclarativeSentence_does_not_add_duplicates()
    {
        val sentence = "Socrates is a man."
        val claim = Claim.fromDeclarativeSentence( "0", sentence )

        claim.addDeclarativeSentence( sentence )

        assertEquals( 1, claim.sentences.size )
        assertEquals( DeclarativeSentence( sentence ), claim.sentences.single() )
    }

    @Test
    fun getSnapshot_to_fromSnapshot_succeeds()
    {
        val claim = Claim.fromDeclarativeSentence( "0", "Socrates is a man." )

        val snapshot = claim.getSnapshot()
        val fromSnapshot = Claim.fromSnapshot( snapshot )

        assertEquals( claim.id, fromSnapshot.id )
        assertEquals( claim.sentences, fromSnapshot.sentences )
    }

    @Test
    fun fromSnapshot_with_no_sentences_fails()
    {
        val snapshot = ClaimSnapshot( "0", emptySet() )

        assertFailsWith<IllegalArgumentException> { Claim.fromSnapshot( snapshot ) }
    }
}
