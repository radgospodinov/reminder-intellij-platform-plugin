package com.github.radgospodinov.plugin.reminder.usecase

import com.github.radgospodinov.plugin.reminder.model.Reminder
import com.github.radgospodinov.plugin.reminder.store.ReminderStore
import com.github.radgospodinov.plugin.reminder.ui.notification.NotificationScheduler
import com.intellij.openapi.project.Project

internal object ShowNotification {
    fun execute(id: String, reminder: Reminder, project: Project) {
        NotificationScheduler.notify(
            reminder.message,
            reminder.timestamp,
            { ReminderStore.instance.toggleMutedState(id) },
            { ShowEditDialog.execute(id) },
            { OpenReminderAnchor.execute(id) },
            project,
        )
    }
}