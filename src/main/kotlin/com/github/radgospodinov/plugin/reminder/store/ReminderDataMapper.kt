package com.github.radgospodinov.plugin.reminder.store

import com.github.radgospodinov.plugin.reminder.model.FileInfo
import com.github.radgospodinov.plugin.reminder.model.Reminder
import com.github.radgospodinov.plugin.reminder.ui.color.HighlightColor
import java.io.File

internal class ReminderDataMapper {

    fun map(dto: ReminderDto): Reminder {
        val url = dto.url ?: ""
        return Reminder(
            FileInfo(
                dto.name ?: url.split("/").last(),
                dto.presentableUrl ?: url.replace("file://", "").replace('/', File.separatorChar),
                dto.url ?: "",
                dto.offset?.toInt() ?: 0,
            ),
            dto.text ?: "",
            dto.timestamp?.toLong() ?: 0L,
            dto.done?.toBooleanStrictOrNull() ?: false,
            HighlightColor.valueOf(dto.highlightColor ?: "DEFAULT")
        )
    }

    fun map(reminder: Reminder): ReminderDto {
        return ReminderDto(
            reminder.fileInfo.name,
            reminder.fileInfo.presentableUri,
            reminder.fileInfo.systemUri,
            reminder.fileInfo.offset.toString(),
            reminder.message,
            reminder.timestamp.toString(),
            reminder.muted.toString(),
            reminder.highlightColor.toString()
        )
    }
}