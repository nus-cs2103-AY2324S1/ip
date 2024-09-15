package duke;

import java.util.Arrays;

import javafx.application.Application;


/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        Application.launch(Main.class, args);
    }
}
