package rock;

import javafx.application.Application;
import rock.ui.Gui;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Alvis Ng (supermii2)
 * Heavily adapted from https://se-education.org/guides/tutorials/javaFx.html
 * Original base (Duke) created by CS2103T team
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
