package com.github.radgospodinov.plugin.reminder.store

import com.github.radgospodinov.plugin.reminder.model.Reminder
import com.github.radgospodinov.plugin.reminder.ui.color.HighlightColor

internal class ReminderDataMapper {

    fun map(dto: ReminderDto): Reminder {
        return Reminder(
            dto.url ?: "",
            dto.offset?.toInt() ?: 0,
            dto.text ?: "",
            dto.timestamp?.toLong() ?: 0L,
            dto.done?.toBooleanStrictOrNull() ?: false,
            HighlightColor.valueOf(dto.highlightColor ?: "DEFAULT")
        )
    }

    fun map(reminder: Reminder): ReminderDto {
        return ReminderDto(
            reminder.locationUrl,
            reminder.offset.toString(),
            reminder.message,
            reminder.timestamp.toString(),
            reminder.muted.toString(),
            reminder.highlightColor.toString()
        )
    }
}