package com.github.radgospodinov.plugin.reminder.ui.color

import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.JBColor
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JList


internal class ColorPicker : ComboBox<HighlightColor>(HighlightColor.values()) {
    init {
        setRenderer(ColorChooserRenderer())
        maximumRowCount = HighlightColor.values().size
    }
}


private class ColorChooserRenderer : DefaultListCellRenderer() {
    override fun getListCellRendererComponent(
        list: JList<*>?,
        value: Any,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus)

        val color: JBColor = (value as HighlightColor).color

        background = if (isSelected) {
            color.darker()
        } else {
            color
        }
        return this
    }
}