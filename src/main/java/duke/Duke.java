package duke;

/**
 * Entry point of the chatbot.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String getWelcome() {
        return ui.showWelcome();
    }
}