import Tasks.*;
import Parser.Parser;
import Ui.Ui;
import Storage.Storage;
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
        this.userUi = new Ui();
        this.tasks = new TaskList();
        this.parser = new Parser();
        this.store = new Storage(dir, tasks);
    }

    /**
     * Starts the main execution of the Corubi chatbot.
     *
     * @throws IOException If an I/O operation is interrupted.
     */
    private static void run() throws IOException {
        userUi.takeCommands(store, tasks, parser);
    }

    /**
     * The entry point of the Corubi application.
     *
     * @param args Command-line arguments (not used in this code).
     * @throws IOException If an I/O operation is interrupted.
     */
    public static void main(String[] args) throws IOException {
        new Corubi(DIRECTORY).run();
    }
}
