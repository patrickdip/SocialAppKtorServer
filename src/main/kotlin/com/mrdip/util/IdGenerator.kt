package com.mrdip.util

import de.mkammerer.snowflakeid.SnowflakeIdGenerator

private val generatorId = System.getenv("id.generator")

object IdGenerator {
    fun generateId(): Long{
        return SnowflakeIdGenerator.createDefault(generatorId.toInt()).next()
    }
}