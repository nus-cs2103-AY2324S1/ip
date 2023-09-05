package duke;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskList;

/**
 * The primary Duke class. Contains the main function to run the Duke chatbot Bob.
 */
public class Duke {

    /** The TaskList to use in the chatbot. */
    private TaskList tasks;

    /** The Storage object handling file read/save. */
    private Storage storage;

    /** The Ui object handling interaction with the user in the chatbot. */
    private Ui ui;

    /**
     * Creates a new Duke chatbot object.
     *
     * @param filePath The file path of the file that will contain all tasks within the chatbot.
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String message = ui.readCommand(sc);
                ui.showLine();
                Command c = Parser.parse(message);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Path path = Paths.get("data", "duke.txt");

        new Duke(path).run();
    }
}
