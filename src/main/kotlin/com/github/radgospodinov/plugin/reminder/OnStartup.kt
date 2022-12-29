package com.github.radgospodinov.plugin.reminder

import com.github.radgospodinov.plugin.reminder.store.ReminderStore
import com.github.radgospodinov.plugin.reminder.usecase.ScheduleNotification
import com.github.radgospodinov.plugin.reminder.usecase.ShowNotification
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

internal class OnStartup : StartupActivity {

    @Volatile
    private var isScheduled = false
    override fun runActivity(project: Project) {
        val state = ReminderStore.instance.reminders().value

        val activeReminders = state.entries.filter { it.value.isActive() }

        if (!isScheduled) {
            activeReminders.forEach { ScheduleNotification.execute(it.key, it.value) }
            isScheduled = true
        } else {
            activeReminders.filter { it.value.isExpired() }
                .forEach {
                    val id = it.key
                    val reminder = it.value

                    ShowNotification.execute(id, reminder, project)
                }
        }
    }
}