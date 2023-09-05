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
     * Starts the Duke application. It displays a welcome message and
     * enters a loop to read user commands until the "bye" command is
     * received, at which point it saves tasks to storage and exits.
     *
     * @throws DukeException If there is an issue with Duke's execution.
     */
    public void start() throws DukeException {
        ui.showWelcomeMessage();
        while (true) {
            String command = ui.getUserInput();
            if (command.equalsIgnoreCase("bye")) {
                ui.showGoodbyeMessage();
                ui.closeScanner();
                break;
            } else {
                try {
                    Parser.parse(command, tasks, ui);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * The main entry point for the Duke application.
     *
     * @param args Command-line arguments (not used in this application).
     * @throws DukeException If there is an issue with Duke's execution.
     */
    public static void main(String[] args) throws DukeException {
        new Duke().start();
    }
}
