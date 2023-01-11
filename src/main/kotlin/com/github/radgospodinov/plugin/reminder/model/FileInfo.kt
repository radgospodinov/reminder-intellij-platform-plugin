package com.github.radgospodinov.plugin.reminder.model

internal data class FileInfo(
    val name: String,
    val presentableUri: String,
    val systemUri: String,
    val offset: Int
)
