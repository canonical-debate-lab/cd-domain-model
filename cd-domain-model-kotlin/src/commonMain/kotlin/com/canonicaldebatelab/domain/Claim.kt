package com.canonicaldebatelab.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * A person or source ([origin]) expressing agreement or disagreement with a [Statement] (i.e., saying it is true or false),
 * as part of which they try to convey a specific meaning (their individual understanding of said statement).
 *
 * While intended meaning can be inferred by other people than those having made the claim, e.g.,
 * by looking at other claims made by the same person or in the same source,
 * ultimately it is the original author of the claim who determines intended meaning.
 */
@Serializable
@SerialName( "Claim" )
data class Claim( val origin: StatementOrigin, val statementId: Id, val isTrue: Boolean )
{
    companion object
    {
        fun fromStatement( origin: StatementOrigin, statement: Statement, isTrue: Boolean ): Claim =
            Claim( origin, statement.id, isTrue )
    }
}


/**
 * The given [statement] is mentioned by this person or source.
 */
fun StatementOrigin.claims( statement: Statement ) = ClaimBuilder( this, statement.id )


class ClaimBuilder( private val origin: StatementOrigin, private val statementId: Id )
{
    /**
     * The [origin] claims the statement is true.
     */
    fun isTrue(): Claim = Claim( origin, statementId, true )

    /**
     * The [origin] claims the statement is false.
     */
    fun isFalse(): Claim = Claim( origin, statementId, false )
}
