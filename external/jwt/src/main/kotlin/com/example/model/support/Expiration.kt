package com.example.model.support

import java.time.temporal.ChronoUnit

data class Expiration(
    val time: Long,
    val unit: ChronoUnit,
)
