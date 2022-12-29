package com.github.radgospodinov.plugin.reminder.ui.notification

import com.github.radgospodinov.plugin.reminder.store.ReminderStore
import com.github.radgospodinov.plugin.reminder.utility.formatTimestamp
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import java.time.format.FormatStyle
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

internal object NotificationScheduler {

    private val scheduler = Executors.newSingleThreadScheduledExecutor()

    fun schedule(
        id: String,
        text: String,
        timestamp: Long,
        onDone: () -> Unit,
        onEdit: () -> Unit,
        onClick: () -> Unit,
    ) {
        val delay = timestamp - System.currentTimeMillis()
        val reminder: () -> Unit = {
            val reminder = ReminderStore.instance.reminderById(id)
            if (reminder?.isActive() == true) {
                ProjectManager.getInstance().openProjects.forEach { proj ->
                    notify(text, timestamp, onDone, onEdit, onClick, proj)
                }
            }
        }

        scheduler.schedule(reminder, delay, TimeUnit.MILLISECONDS)
    }

    fun notify(
        text: String,
        timestamp: Long,
        onDone: () -> Unit,
        onEdit: () -> Unit,
        onClick: () -> Unit,
        project: Project? = null
    ) {
        val exp = if (System.currentTimeMillis() < timestamp) "Expires in" else "Expired on"
        val alarmTime = " $exp ${timestamp.formatTimestamp(FormatStyle.MEDIUM)}"

        val notification = ReminderNotification(text, alarmTime, onDone, onEdit, onClick)
        notification.notify(project)
    }

}