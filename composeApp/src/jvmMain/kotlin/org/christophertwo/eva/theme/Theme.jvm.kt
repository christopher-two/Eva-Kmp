package org.christophertwo.eva.theme

import androidx.compose.runtime.Composable
import java.awt.event.HierarchyEvent
import java.awt.event.HierarchyListener
import javax.swing.JDialog
import javax.swing.SwingUtilities
import javax.swing.UIManager

@Composable
internal actual fun SystemAppearance(isDark: Boolean) {
    val dialog = JDialog()
    dialog.addHierarchyListener(object : HierarchyListener {
        override fun hierarchyChanged(e: HierarchyEvent) {
            val isDarkMode = UIManager.getLookAndFeel().name.contains("Dark")
            // You can now use the isDarkMode value to adjust your theme
            // For example, by calling a function that updates the theme based on this value.
            // For simplicity, we'll just print it here.
            println("System Appearance: ${if (isDarkMode) "Dark" else "Light"}")
            dialog.removeHierarchyListener(this) // Remove listener after first check
        }
    })
    // Trigger the hierarchy listener by making the dialog visible (and then immediately disposing it)
    SwingUtilities.invokeLater {
        dialog.pack()
        dialog.isVisible = false // Keep it invisible
        dialog.dispose()
    }
}