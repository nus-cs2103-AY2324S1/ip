package shiba;

import javafx.application.Application;
import shiba.parsers.CommandParser;
import shiba.tasks.FilePersistentTaskList;
import shiba.ui.MainWindow;
import shiba.ui.Replier;

/**
 * Represents the main class of the Shiba chatbot.
 */
public class Shiba {
    private static Shiba singleton;
    private final String name;
    private final CommandParser parser;

    private Shiba(String name, String dataPath) {
        this.name = name;
        parser = new CommandParser(new FilePersistentTaskList(dataPath));

        assert singleton == null : "Multiple instances of Shiba detected!";
        singleton = this;
    }

    public void start() {
        Replier.printGreeting(name);
    }

    private void stop() {
        Replier.printBye();
    }

    /**
     * Processes the user input received from UI window.
     *
     * @param input The user input.
     */
    public void processUserInput(String input) {
        boolean isQuitRequested = parser.processUserInput(input);
        MainWindow mainWindow = MainWindow.getInstance();
        if (isQuitRequested && mainWindow != null) {
            stop();
            mainWindow.close();
        }
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        new Shiba("SHIBA-BOT", "./shibaData/tasks.txt");

        Application.launch(MainWindow.class);

        MainWindow.getInstance().cleanUp();
    }

    /**
     * Returns the singleton instance of Shiba.
     *
     * @return The singleton instance of Shiba.
     */
    public static Shiba getInstance() {
        return singleton;
    }
}
