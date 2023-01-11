package com.github.radgospodinov.plugin.reminder.usecase

import com.github.radgospodinov.plugin.reminder.model.FileInfo
import com.github.radgospodinov.plugin.reminder.model.Reminder
import com.github.radgospodinov.plugin.reminder.store.ReminderStore
import com.github.radgospodinov.plugin.reminder.ui.notification.ErrorNotification
import com.github.radgospodinov.plugin.reminder.utility.extractSelectedText
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

internal object CreateReminder {
    fun execute(actionEvent: AnActionEvent, timestamp: Long, text: String = actionEvent.extractSelectedText()) {
        val file: PsiFile? = actionEvent.getData(CommonDataKeys.PSI_FILE)
        if (file?.virtualFile == null) {
            ErrorNotification("Can't add reminder. <br> Possible solution: Save project files to disk and retry.")
                .notify(null)
            return
        }

        val editor: Editor = actionEvent.getRequiredData(CommonDataKeys.EDITOR)

        val reminder = Reminder(
            FileInfo(
                file.virtualFile.name,
                file.virtualFile.presentableUrl,
                file.virtualFile.url,
                editor.caretModel.offset
            ),
            message = text,
            timestamp = timestamp,
        )

        ReminderStore.instance.addReminder(reminder)
    }
}