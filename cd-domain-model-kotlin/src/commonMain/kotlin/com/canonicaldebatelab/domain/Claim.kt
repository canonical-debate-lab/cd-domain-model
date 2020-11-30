package com.canonicaldebatelab.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * Anything which can be said to be true or false, regardless of whether it is fact or opinion,
 * and can be stated using one or more interchangeable declarative [sentences].
 *
 * If the intent behind what is being said is practically the same, we consider it the same claim (with the same [id]).
 */
class Claim private constructor( val id: String, sentences: Set<DeclarativeSentence> )
{
    init { require( sentences.isNotEmpty() ) }

    private val _sentences: MutableSet<DeclarativeSentence> = sentences.toMutableSet()
    val sentences: Set<DeclarativeSentence>
        get() = _sentences.toSet()


    companion object
    {
        fun fromDeclarativeSentence( id: String, sentence: String ): Claim
        {
            val sentences = setOf( DeclarativeSentence( sentence ) )
            return Claim( id, sentences )
        }

        fun fromSnapshot( snapshot: ClaimSnapshot ): Claim = Claim( snapshot.id, snapshot.sentences )
    }


    fun getSnapshot(): ClaimSnapshot = ClaimSnapshot( id, sentences )

    /**
     * Add a declarative [sentence] to the set of interchangeable sentences which can be used to express this claim.
     */
    fun addDeclarativeSentence( sentence: String ) = _sentences.add( DeclarativeSentence( sentence ) )
}


@Serializable
@SerialName( "Claim" )
data class ClaimSnapshot( val id: String, val sentences: Set<DeclarativeSentence> )
