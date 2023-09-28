package potato;

import java.io.IOException;

import potato.command.Command;

/**
 * The Potato class represents the main logic of the Potato application.
 * It manages interactions between the user interface, task storage, and task list.
 */
public class Potato {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Potato object and initializes the storage, user interface, and task list.
     * If a saved task list exists, it loads it;
     * otherwise, it creates a new empty task list.
     */
    public Potato() {
        storage = new Storage("./Potato.txt");
        ui = new Ui();
        try {
            tasks = new TaskList(storage.loadTask());
            System.out.println("here's the saved list");
        } catch (IOException e) {
            System.out.println("No saved list");
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Processes user input and generates a response.
     *
     * @param input The user input to be processed.
     * @return A response message generated based on the user input.
     */
    String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response += c.execute(tasks, ui, storage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

}
