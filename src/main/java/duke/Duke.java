package duke;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.command.Command;
import duke.parser.Parser;

import java.io.IOException;

/**
 * Represent the ChatBot.
 * Run the ChatBot to intereact with it.
 * Contains a list of tasks, TaskList, a user interface Ui, and storage, Storage.
 */
public class Duke {
    private static final String filePath = "./duke.txt";
    private final Ui ui;
    private TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a new ChatBot to chat with.
     * @param name Name of the ChatBot
     */
    public Duke(String name) {
        this.ui = new Ui(name);
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the ChatBot.
     */
    public void run() {
        ui.showWelcome();
        while (ui.hasNextLine()) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * @param args Main method to run ChatBot.
     */
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
