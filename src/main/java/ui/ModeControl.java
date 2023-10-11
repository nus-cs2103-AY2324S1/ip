package ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * The abstract base class for controlling the application's display mode.
 *
 * @author Ho Khee Wei
 */
public abstract class ModeControl {
    private static Pane window;
    private static boolean isDark = true;

    /**
    * Sets the main window (root pane) of the application's graphical user interface.
    *
    * @param window The main window (root pane) to be controlled for mode changes.
    */
    public static void setWindow(Pane window) {
        ModeControl.window = window;
    }

    /**
    * Toggles between dark mode and light mode for the entire graphical user interface.
    *
    * @return A string indicating the current mode after toggling ("dark" or "light").
    */
    public static String toggle() {
        if (isDark) {
            isDark = false;
            setLightRecursively(window);
            return "light";
        } else {
            isDark = true;
            setDarkRecursively(window);
            return "dark";
        }
    }

    /**
    * Recursively sets the graphical user interface to light mode, starting from the specified pane.
    *
    * @param pane The root pane from which to start applying light mode.
    */
    private static void setLightRecursively(Pane pane) {
        pane.getStyleClass().add("light-mode");

        for (Node node : pane.getChildren()) {
            if (node instanceof Pane) {
                Pane childPane = (Pane) node;
                setLightRecursively(childPane);
            } else {
                node.getStyleClass().add("light-mode");
            }
        }

    }

    /**
     * Recursively sets the graphical user interface to dark mode, starting from the specified pane.
     *
     * @param pane The root pane from which to start applying dark mode.
     */
    private static void setDarkRecursively(Pane pane) {
        pane.getStyleClass().remove("light-mode");

        for (Node node : pane.getChildren()) {
            if (node instanceof Pane) {
                Pane childPane = (Pane) node;
                setLightRecursively(childPane);
            } else {
                node.getStyleClass().remove("light-mode");
            }
        }

    }

}
