package rock;

import rock.ui.Gui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}