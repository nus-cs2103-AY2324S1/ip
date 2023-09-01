package main;
import tasks.*;
import parser.Parser;
import ui.Ui;
import storage.Storage;
import java.io.IOException;

public class Corubi {
    private static Ui userUi;
    private static Storage store;
    private static TaskList tasks;
    private static Parser parser;

    private static final String DIRECTORY = "./src/main/java/OUTPUT.txt";

    /**
     * Constructs a Corubi instance.
     *
     * @param dir The directory path for storing data.
     */
    private Corubi(String dir) {
        userUi = new Ui();
        tasks = new TaskList();
        parser = new Parser();
        store = new Storage(dir, tasks);
    }

    /**
     * Starts the main execution of the Corubi chatbot.
     *
     * @throws IOException If an I/O operation is interrupted.
     */
    static void run() throws IOException {
        userUi.takeCommands(store, tasks, parser);
    }

    /**
     * The entry point of the Corubi application.
     *
     * @param args Command-line arguments (not used in this code).
     * @throws IOException If an I/O operation is interrupted.
     */
    public static void main(String[] args) throws IOException {
        new Corubi(DIRECTORY);
        run();
    }
}
