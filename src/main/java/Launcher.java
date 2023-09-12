import duke.Main;
import javafx.application.Application;

import java.util.Arrays;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        Application.launch(Main.class, args);
    }
}
