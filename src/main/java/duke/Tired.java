package duke;
import javafx.application.Platform;

enum TaskType {
    TODO, DEADLINE, EVENT
}

public class Tired {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a `Tired` chatbot object.
     */
    public Tired() {
        ui = new Ui();
        storage = new Storage("duke.txt");
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
        if (input.trim().equals("bye")) {
//            Platform.exit();
            return ui.showGoodbyeMessage();
        }
        try {
            return Parser.parseCommand(input, tasks, ui);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}