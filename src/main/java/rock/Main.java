package rock;

import javafx.application.Application;
import rock.ui.Gui;

/**
 * A launcher class to workaround classpath issues.
 * Heavily adapted from
 * https://se-education.org/guides/tutorials/javaFx.html
 * Original base (Duke) created by CS2103T team
 * @author Alvis Ng (supermii2)
 */
public class Main {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
