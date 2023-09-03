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
    public static Shiba SINGLETON;
    private final String name;
    private final CommandParser parser;

    private Shiba(String name, String dataPath) {
        this.name = name;
        parser = new CommandParser(new FilePersistentTaskList(dataPath));

        if (SINGLETON == null) {
            SINGLETON = this;
        } else {
            System.out.println("Warning: Multiple instances of Shiba detected!");
        }
    }

    private void start() {
        Replier.printGreeting(name);
    }

    private void stop() {
        Replier.printBye();
    }

    public static void main(String[] args) {
        Shiba shiba = new Shiba("SHIBA-BOT", "./shibaData/tasks.txt");

        new Thread(() -> Application.launch(MainWindow.class)).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        shiba.start();
    }

    /**
     * Processes the user input received from UI window.
     *
     * @param input The user input.
     */
    public void processUserInput(String input) {
        boolean quit = parser.processUserInput(input);
        if (quit && MainWindow.SINGLETON != null) {
            stop();
            MainWindow.SINGLETON.close();
        }
    }

    public String getName() {
        return name;
    }
}
