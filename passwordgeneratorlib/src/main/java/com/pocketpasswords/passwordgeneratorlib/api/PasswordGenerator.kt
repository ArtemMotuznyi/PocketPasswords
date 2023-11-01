package com.pocketpasswords.passwordgeneratorlib.api

import com.pocketpasswords.passwordgeneratorlib.api.model.Password
import kotlinx.coroutines.flow.StateFlow

interface PasswordGenerator {

    val rules: StateFlow<List<Rule>>

    fun generate(): Password

    fun addRule(rule: Rule)
    fun removeRule(rule: Rule)

    fun setLength(length: Int)

    fun getLength(): Int

}