package com.canonicaldebatelab.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * A single origin of a [Statement].
 * I.e., a specific person or source that made the statement.
 */
@Serializable
@SerialName( "Origin" )
sealed class StatementOrigin
{
    @Serializable
    @SerialName( "User" )
    data class User( val userId: Id ) : StatementOrigin()

    @Serializable
    @SerialName( "Source" )
    data class Source( val sourceId: Id ) : StatementOrigin()
}
