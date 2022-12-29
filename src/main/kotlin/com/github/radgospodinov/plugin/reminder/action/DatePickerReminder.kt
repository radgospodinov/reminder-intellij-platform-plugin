package com.github.radgospodinov.plugin.reminder.action

import com.github.radgospodinov.plugin.reminder.ui.calendar.CalendarDialog
import com.github.radgospodinov.plugin.reminder.usecase.CreateReminder
import com.github.radgospodinov.plugin.reminder.utility.extractSelectedText
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

internal class DatePickerReminder : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val selectedText: String = event.extractSelectedText()

        val calendar = CalendarDialog(selectedText)
        if (calendar.showAndGet()) {
            val dateTime: LocalDateTime = calendar.dateTime

            val reminderText: String = calendar.text
            val systemZone: ZoneId = ZoneId.systemDefault()
            val currentOffsetForMyZone: ZoneOffset = systemZone.rules.getOffset(dateTime)

            CreateReminder.execute(
                event,
                dateTime.toInstant(currentOffsetForMyZone).toEpochMilli(),
                reminderText
            )
        }
    }

}