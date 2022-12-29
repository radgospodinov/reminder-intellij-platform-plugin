package com.github.radgospodinov.plugin.reminder.action

import com.github.radgospodinov.plugin.reminder.usecase.CreateReminder
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

internal class TomorrowReminder : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        val dateTime = LocalDateTime.now()
            .plusDays(1)
            .withHour(10)
            .withMinute(0)
            .withSecond(0)

        val currentOffset: ZoneOffset = ZoneId.systemDefault().rules.getOffset(dateTime)

        CreateReminder.execute(actionEvent, dateTime.toInstant(currentOffset).toEpochMilli())
    }
}