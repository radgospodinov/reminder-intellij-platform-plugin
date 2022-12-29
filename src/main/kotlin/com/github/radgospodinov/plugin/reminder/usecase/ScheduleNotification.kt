package com.github.radgospodinov.plugin.reminder.usecase

import com.github.radgospodinov.plugin.reminder.model.Reminder
import com.github.radgospodinov.plugin.reminder.store.ReminderStore
import com.github.radgospodinov.plugin.reminder.ui.notification.NotificationScheduler

internal object ScheduleNotification {
    fun execute(id: String, reminder: Reminder) {
        NotificationScheduler.schedule(
            id,
            reminder.message,
            reminder.timestamp,
            { ReminderStore.instance.toggleMutedState(id) },
            { ShowEditDialog.execute(id) }
        ) { OpenReminderAnchor.execute(id) }
    }
}