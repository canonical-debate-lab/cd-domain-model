package com.canonicaldebatelab.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Anything which can be said to be true or false, regardless of whether it is fact or opinion,
 * and regardless of whether people share the same intent when they say it is true or false.
 *
 * A statement can be stated using one or more interchangeable declarative [sentences].
 * "Interchangeable" means it is expected that when somebody states one sentence is
 * true or false—regardless of whether this is something very vague or precise—they would
 * make the same assessment about the other sentences.
 */
class Statement private constructor( val id: Id, sentences: Set<DeclarativeSentence> )
{
    init { require( sentences.isNotEmpty() ) }

    private val _sentences: MutableSet<DeclarativeSentence> = sentences.toMutableSet()
    val sentences: Set<DeclarativeSentence>
        get() = _sentences.toSet()


    companion object
    {
        fun fromDeclarativeSentence( id: Id, sentence: String ): Statement
        {
            val sentences = setOf( DeclarativeSentence( sentence ) )
            return Statement( id, sentences )
        }

        fun fromSnapshot( snapshot: StatementSnapshot ): Statement = Statement( snapshot.id, snapshot.sentences )
    }


    fun getSnapshot(): StatementSnapshot = StatementSnapshot( id, sentences )

    /**
     * Add a declarative [sentence] to the set of interchangeable sentences which can be used to express this claim.
     */
    fun addDeclarativeSentence( sentence: String ) = _sentences.add( DeclarativeSentence( sentence ) )
}


@Serializable
@SerialName( "Statement" )
data class StatementSnapshot( val id: Id, val sentences: Set<DeclarativeSentence> )
