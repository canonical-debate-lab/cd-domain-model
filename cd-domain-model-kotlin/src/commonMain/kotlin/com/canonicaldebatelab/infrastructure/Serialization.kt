package com.canonicaldebatelab.infrastructure

import kotlinx.serialization.json.Json


/**
 * Initialize a JSON serializer conforming to the Canonical Debate Lab agreed upon formatting standards.
 */
fun createDefaultJSON(): Json = Json { }
