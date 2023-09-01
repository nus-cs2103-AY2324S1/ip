package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * CS2103T iP Week 3
 * AY23/24 Semester 1
 * A product named Duke, a Personal Assistant Chatbot that helps
 * a person to keep track of various things.
 *
 * @author bhnuka, Bhanuka Bandara Ekanayake (AXXX7875J), G01
 * @version 2.0 CS2103T AY 23/24 Sem 1
 */

/**
 * All the sourcecode behind the chatbot, Duke
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot, allowing user interaction.
     * Displays a welcome message and processes user commands until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method to start the Duke chatbot.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}