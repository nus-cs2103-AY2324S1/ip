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
    private final String name;
    private final CommandParser parser;

    /**
     * Creates a new Shiba object.
     *
     * @param name The name of the bot.
     * @param dataPath Path to the file where tasks are saved.
     */
    public Shiba(String name, String dataPath) {
        this.name = name;
        parser = new CommandParser(new FilePersistentTaskList(dataPath));
    }

    /**
     * Starts the bot.
     */
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
        Application.launch(MainWindow.class);

        MainWindow.getInstance().cleanUp();
    }
}
