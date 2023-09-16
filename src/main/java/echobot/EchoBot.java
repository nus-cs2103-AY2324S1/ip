package echobot;

import java.io.File;

import echobot.utilities.Input;
import echobot.utilities.Parser;
import echobot.utilities.Storage;
import echobot.utilities.TaskList;
import echobot.utilities.Ui;

/**
 * The main class for EchoBot Chatbot
 */
public class EchoBot {

    /** Variable to show if EchoBot is running or not */
    private static boolean isRunning = false;

    /** File path to the tasks.txt */
    private static final String FILE_PATH = "./tasks.txt";

    /** Variable to store task list */
    private Storage storage;

    /** Variable to handle list of tasks operations */
    private TaskList tasks;

    /** Variable to handle user interactions */
    private Ui ui;

    /** Variable to handle user inputs */
    private Parser parser;

    /**
     * Creates a new instance of EchoBot chatbot
     */
    public EchoBot() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(FILE_PATH);
        if (storage.fileExists()) {
            tasks = new TaskList(storage.loadTasksData());
        } else {
            tasks = new TaskList();
        }
        File txtFile = new File(FILE_PATH);
        assert txtFile.exists();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Input parsedInput = parser.parse(input);
        String output = parser.handleInput(tasks, parsedInput, ui);
        File txtFile = new File(FILE_PATH);
        assert txtFile.exists();
        tasks.overwriteTasksData(storage);
        return output;
    }

    /**
     * Starts the current session
     */
    public static void startBot() {
        isRunning = true;
    }

    /** Ends the current session */
    public static void stopBot() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
