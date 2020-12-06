package com.canonicaldebatelab.domain

import kotlin.test.*


/**
 * Tests for [Statement].
 */
class StatementTest
{
    @Test
    fun fromDeclarativeSentence_succeeds()
    {
        val id = "0"
        val sentence = "Socrates is a man."
        val statement = Statement.fromDeclarativeSentence( id, "Socrates is a man." )

        assertEquals( id, statement.id )
        assertEquals( 1, statement.sentences.size )
        assertEquals( DeclarativeSentence( sentence ), statement.sentences.single() )
    }

    @Test
    fun addDeclarativeSentence_succeeds()
    {
        val statement = Statement.fromDeclarativeSentence( "0", "Socrates is a man." )

        val additionalSentence = "Socrates, the Greek philosopher, is a man."
        statement.addDeclarativeSentence( additionalSentence )

        assertEquals( 2, statement.sentences.size )
        assertTrue( DeclarativeSentence( additionalSentence ) in statement.sentences )
    }

    @Test
    fun addDeclarativeSentence_does_not_add_duplicates()
    {
        val sentence = "Socrates is a man."
        val statement = Statement.fromDeclarativeSentence( "0", sentence )

        statement.addDeclarativeSentence( sentence )

        assertEquals( 1, statement.sentences.size )
        assertEquals( DeclarativeSentence( sentence ), statement.sentences.single() )
    }

    @Test
    fun getSnapshot_to_fromSnapshot_succeeds()
    {
        val statement = Statement.fromDeclarativeSentence( "0", "Socrates is a man." )

        val snapshot = statement.getSnapshot()
        val fromSnapshot = Statement.fromSnapshot( snapshot )

        assertEquals( statement.id, fromSnapshot.id )
        assertEquals( statement.sentences, fromSnapshot.sentences )
    }

    @Test
    fun fromSnapshot_with_no_sentences_fails()
    {
        val snapshot = StatementSnapshot( "0", emptySet() )

        assertFailsWith<IllegalArgumentException> { Statement.fromSnapshot( snapshot ) }
    }
}
