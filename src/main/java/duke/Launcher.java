package duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    // DUKE_ARGS contains the required Strings to make Duke adaptable for different file paths and logos.
    public static final String[] DUKE_ARGS = {
        "./data/tasks.txt",
        "/images/DaUser.png",
        "/images/DaDuke.png",
        " /$$              /$$                \n"
            + "| $$              | $$                \n"
            + "| $$ /$$   /$$| $$   /$$  /$$$$$$ \n"
            + "| $$| $$    | $$| $$  /$$/ /$$__    $$\n"
            + "| $$| $$    | $$| $$$$$$/ | $$$$$$$$\n"
            + "| $$| $$    | $$| $$_  $$ | $$_____/\n"
            + "| $$|  $$$$$$/| $$ \\  $$|  $$$$$$$\n"
            + "|__/ \\______/ |__/  \\__/ \\_______/"
    };
    public static void main(String[] args) {
        Application.launch(Duke.class, DUKE_ARGS);
    }
}
