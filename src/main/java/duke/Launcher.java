package duke;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static final String[] dukeArgs = {
        "./data/tasks.txt",
        "/images/DaUser.png",
        "/images/DaDuke.png",
        " /$$              /$$                \n" +
            "| $$              | $$                \n" +
            "| $$ /$$   /$$| $$   /$$  /$$$$$$ \n" +
            "| $$| $$    | $$| $$  /$$/ /$$__    $$\n" +
            "| $$| $$    | $$| $$$$$$/ | $$$$$$$$\n" +
            "| $$| $$    | $$| $$_  $$ | $$_____/\n" +
            "| $$|  $$$$$$/| $$ \\  $$|  $$$$$$$\n" +
            "|__/ \\______/ |__/  \\__/ \\_______/"
    };
        public static void main(String[] args) {
            Application.launch(Duke.class, dukeArgs);
    }
}