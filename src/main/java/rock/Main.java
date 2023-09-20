package rock;

import javafx.application.Application;
import rock.ui.Gui;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Alvis Ng (supermii2)
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
