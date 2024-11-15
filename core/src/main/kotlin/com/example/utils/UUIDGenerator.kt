package com.example.utils

import com.github.f4b6a3.uuid.alt.GUID
import java.util.*

object UUIDGenerator {
    fun newV7UUID(): UUID = GUID.v7().toUUID()
}
