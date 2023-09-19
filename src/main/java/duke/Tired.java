package duke;

enum TaskType {
    TODO, DEADLINE, EVENT
}

/**
 * The Tired class is the main class for the Tired chatbot.
 */
public class Tired {
    private Storage storage;
    private Storage archive;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a `Tired` chatbot object.
     */
    public Tired() {
        ui = new Ui();
        storage = new Storage("duke.txt");
        archive = new Storage("archive.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Processes the user's input and returns a response.
     *
     * @param input The user's input command.
     * @return The response message generated based on the input.
     */
    String getResponse(String input) {
        try {
            if (input.trim().equals("archive list")) {
                archive.saveToFile(tasks);
                tasks = new TaskList();
                storage.saveToFile(tasks);
                return ui.showArchiveMessage();
            }

            if (input.trim().equals("bye")) {
                storage.saveToFile(tasks);
                return ui.showGoodbyeMessage();
            }
            return Parser.parseCommand(input, tasks, ui);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
