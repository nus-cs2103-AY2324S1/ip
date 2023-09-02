package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The Duke program is a chatbot named Beep Boop Bot that
 * executes commands to create and edit a tasklist.
 *
 * @author Inez Kok
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * This is the constructor for a Duke.
     *
     * @param filePath This is the string representaion of the file path used to store the TaskList.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * This method is used to run the Duke program.
     */
    public void run() {
        ui.printGreetingMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input, tasks.size());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * This is the main method which makes use of the run method.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
