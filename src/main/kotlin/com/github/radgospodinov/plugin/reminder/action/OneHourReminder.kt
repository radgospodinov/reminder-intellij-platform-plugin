package com.github.radgospodinov.plugin.reminder.action

import com.github.radgospodinov.plugin.reminder.usecase.CreateReminder
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import java.util.concurrent.TimeUnit

internal class OneHourReminder : AnAction() {
    override fun actionPerformed(actionEvent: AnActionEvent) {
        CreateReminder.execute(actionEvent, System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1))
    }
}