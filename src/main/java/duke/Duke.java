package duke;

import java.util.ArrayList;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.EmptyTaskException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidIndexException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;


/**
 * Duke is a command-line task management application that allows users to manage tasks.
 * It provides functionality to add tasks, mark tasks as done, delete tasks, and more.
 * This class serves as the entry point of the application and manages its execution.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new instance of the Duke application.
     * Initializes the user interface, storage, and task list.
     * Loads task from storage into the task list.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        ArrayList<Task> tasks = storage.load();
        this.tasks = new TaskList(tasks);
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the main loop of the Duke application.
     * Displays a welcome message and processes user input until an exit command is encountered.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.getUserInput();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | InvalidIndexException | EmptyTaskException | InvalidDateTimeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                ui.showLine();
            }


        }
    }

}
