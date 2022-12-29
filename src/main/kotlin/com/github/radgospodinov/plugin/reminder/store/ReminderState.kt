package com.github.radgospodinov.plugin.reminder.store

import java.io.Serializable

data class ReminderState(
    var reminders: MutableMap<String, ReminderDto> = HashMap()
) : Serializable