package woofwoof;

import java.util.Objects;

import javafx.application.Application;
import woof.Woof;

/**
 * The Launcher class is responsible for launching the WoofWoof application.
 */
public class Launcher {
    /**
     * The main method of the Launcher class.
     *
     * @param args The command-line arguments. If 'cli' is provided as an argument, it launches the CLI mode;
     *             otherwise, it launches the GUI mode.
     */
    public static void main(String[] args) {
        if (args.length != 1 || !Objects.equals(args[0], "cli")) {
            Application.launch(WoofWoof.class, args);
        } else {
            Woof.main(null);
        }
    }
}
