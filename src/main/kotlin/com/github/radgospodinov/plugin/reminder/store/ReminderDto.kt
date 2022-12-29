package com.github.radgospodinov.plugin.reminder.store

import java.io.Serializable

data class ReminderDto(
    var url: String? = null,
    var offset: String? = null,
    var text: String? = null,
    var timestamp: String? = null,
    var done: String? = null,
    var highlightColor: String? = null,
) : Serializable