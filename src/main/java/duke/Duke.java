package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

/**
 * Duke is a personal assistant chatbot that helps
 * to keep track of various tasks.
 *
 */
public class Duke {
    public static final String FILE_PATH = "data/duke.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Duke object.
     *
     * @param filePath The filepath for loading and saving the file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke application, handling user interactions.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit) {
            try {
                // Get the next task input
                String command = scanner.nextLine();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * The main method to start the Duke application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}
