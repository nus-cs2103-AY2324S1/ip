package duke;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;
import duke.util.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Main to class to handle duke operations
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    /**
     * Constructor
     */
    public Duke() {
        storage = new Storage();
        parser = new Parser();
        ui = new Ui();
        try {
            String tasksFromStorage = storage.handleReadAllTasksFromFile();
            tasks = new TaskList(tasksFromStorage);
        } catch (DukeException | IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String[] getResponse(String input) {
        if (input.equals("bye")) {
            return new String[]{ui.exit(), "nonError"};
        }
        try {
            String[] commandType = parser.handleUserInput(input);
            String str = handleCommand(commandType[0], commandType[1]);
            ArrayList<Task>currentTasks = tasks.getTasks();
            storage.handleChangesInFile(currentTasks);
            return new String[]{str, "nonError"};
        } catch (Exception e) {
            return new String[]{e.getMessage(), "error"};
        }
    }
    /**
     * Method to handle input
     * @param command command of user
     * @param input inout of user
     * @return String representing the process performed
     * @throws DukeException
     * @throws IOException
     */

    public String handleCommand(String command, String input) throws DukeException, IOException {
        if (input.equals("")) {
            throw new DukeException("SUI, Invalid Command!");
        }

        switch (command) {
        case "mark":
            return tasks.markTask(input);
        case "unmark":
            return tasks.unmarkTask(input);
        case "list":
            return tasks.getAllToDo();
        case "todo":
            return tasks.handleTodoTask(input);
        case "deadline":
            return tasks.handleDeadlineTask(input, "user");
        case "event":
            return tasks.handleEventTask(input, "user");
        case "delete":
            return tasks.deleteTask(input);
        case "find":
            return tasks.handleFindTask(input);
        default:
            throw new DukeException("SUI, Invalid Command!");
        }
    }
}
