package com.github.radgospodinov.plugin.reminder.model

import com.github.radgospodinov.plugin.reminder.ui.color.HighlightColor

internal data class Reminder(
    val fileInfo: FileInfo,
    val message: String,
    val timestamp: Long,
    val muted: Boolean = false,
    val highlightColor: HighlightColor = HighlightColor.DEFAULT,
) {
    fun isExpired() = timestamp <= System.currentTimeMillis()

    fun isActive(): Boolean = !muted
}
