package Duke;

import java.io.IOException;
import java.io.File;

/**
 * The Duke class represents the main class for the Duke chat-bot.
 * It contains the main method and handles the initialization of the chat-bot.
 */
public class Duke {
    private Ui ui = new Ui();
    private TaskList tasks;

    /**
     * Constructs a new Duke object and initializes the chat-bot.
     */
    public Duke() {
        System.out.println("Hello! I'm Auntie Maggie " +
                "\nWhat can I do for you?");
        Storage storage = new Storage("./data/duke.txt");
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
        ui.getInput(tasks, storage);
    }
    public static void main(String[] args) {
        new Duke();
    }
}

