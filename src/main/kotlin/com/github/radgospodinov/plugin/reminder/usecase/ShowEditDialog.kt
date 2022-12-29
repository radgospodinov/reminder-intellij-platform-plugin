package com.github.radgospodinov.plugin.reminder.usecase

import com.github.radgospodinov.plugin.reminder.store.ReminderStore
import com.github.radgospodinov.plugin.reminder.ui.calendar.CalendarDialog
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

internal object ShowEditDialog {
    fun execute(id: String) {
        val reminder = ReminderStore.instance.reminderById(id) ?: return

        val calendar = CalendarDialog(reminder.message, reminder.timestamp)
        if (calendar.showAndGet()) {
            val dateTime: LocalDateTime = calendar.dateTime
            val systemZone: ZoneId = ZoneId.systemDefault()
            val currentOffsetForMyZone: ZoneOffset = systemZone.rules.getOffset(dateTime)

            ReminderStore.instance.editReminder(
                id,
                dateTime.toInstant(currentOffsetForMyZone).toEpochMilli(),
                calendar.text
            )
        }
    }
}