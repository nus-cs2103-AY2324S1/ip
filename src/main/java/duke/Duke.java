package duke;

import java.io.IOException;

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
            tasks = new TaskList(storage.handleReadAllTasksFromFile());
        } catch (DukeException | IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        if (input.equals("bye")) {
            return ui.exit();
        }
        try {
            String[] commandType = parser.handleUserInput(input);
            String str = handleCommand(commandType[0], commandType[1]);
            storage.handleChangesInFile(tasks.getTasks());
            return str;
        } catch (Exception e) {
            return e.getMessage();
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
            throw new DukeException("Invalid Command!");
        }

        switch (command) {
        case "mark":
            return tasks.markTask(input);
        case "unmark":
            return tasks.unmarkTask(input);
        case "list":
            return tasks.getAllToDo();
        case "todo":
            return tasks.handleTodoTask(input, "user");
        case "deadline":
            return tasks.handleDeadlineTask(input, "user");
        case "event":
            return tasks.handleEventTask(input, "user");
        case "delete":
            return tasks.deleteTask(input);
        case "find":
            return tasks.handleFindTask(input);
        default:
            throw new DukeException("Invalid Command!");
        }
    }
}
