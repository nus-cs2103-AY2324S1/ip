package duke;

import duke.task.TaskList;

/**
 * Duke is a task management application that allows users to
 * manage their tasks by adding, deleting, and marking tasks as done.
 * It provides a command-line interface for user interaction.
 */
public class Duke {

    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a Duke object and initializes the user interface (UI),
     * storage, and task list. It attempts to load tasks from storage
     * and handles exceptions if loading fails.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Retrieves the welcome message from the user interface.
     *
     * @return A welcome message as a string.
     */
    public String welcomeMessage() {
        return ui.showWelcomeMessage();
    }

    /**
     * Processes user input and returns a response.
     *
     * @param input The user's input command.
     * @return The response message as a string.
     */
    public String getResponse(String input) {
        try {
            if (input.equalsIgnoreCase("save")) {
                storage.saveTasks(tasks.getTasks());
                return "List saved!";
            } else {
                String response = Parser.parse(input, tasks, ui);
                return response;
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}