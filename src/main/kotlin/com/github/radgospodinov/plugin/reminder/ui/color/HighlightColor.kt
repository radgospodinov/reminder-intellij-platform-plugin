package com.github.radgospodinov.plugin.reminder.ui.color

import com.intellij.ui.JBColor

enum class HighlightColor(val color: JBColor) {
    DEFAULT(JBColor.LIGHT_GRAY),
    BLUE(JBColor.BLUE),
    CYAN(JBColor.CYAN as JBColor), // JBColor constant has inconsistent type
    GREEN(JBColor.GREEN),
    MAGENTA(JBColor.MAGENTA as JBColor), // JBColor constant has inconsistent type
    ORANGE(JBColor.ORANGE),
    YELLOW(JBColor.YELLOW),
}