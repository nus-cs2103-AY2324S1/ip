import eggbot.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * The main entrypoint to the program.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
