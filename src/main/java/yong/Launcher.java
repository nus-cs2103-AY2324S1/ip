package yong;

import javafx.application.Application;
import yong.ui.Bridge;


/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Bridge.class, args);
    }
}
