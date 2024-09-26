package aichan;

import aichan.command.Command;

/**
 * Represents the class of the chatbot AiChan.
 */
public class AiChan {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an AiChan object with the given filePath.
     * Initialize user interface, storage, and task lists.
     *
     * @param filePath File path for storing and loading tasks.
     */
    public AiChan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (AiChanException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns response of AiChan according to user's input.
     *
     * @param input The user input.
     * @return String with AiChan's response.
     */
    public String getResponse(String input) {
        String aiChanResponse = "you find a bug, please inform the developer~";
        try {
            Command c = Parser.parse(input);
            aiChanResponse = c.execute(tasks, ui, storage);
        } catch (AiChanException e) {
            aiChanResponse = e.getMessage();
        }
        return aiChanResponse;
    }
}
