package spot;


import spot.command.Command;
import spot.exception.SpotException;

/**
 * Represents a chatbot named Spot.
 */
public class Spot {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private boolean isExit;

    /**
     * Constructs a new Spot object.
     */
    public Spot() {
        try {
            ui = new Ui();
            storage = new Storage();
            tasks = new TaskList(storage.loadTasks());
        } catch (SpotException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns String response from the Spot chatbot.
     */
    public String getResponse(String input) {
        try {
            ui.resetOutput();
            Command c = Parser.parseCommand(input);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            return ui.getOutput();
        } catch (SpotException e) {
            ui.setError(e.getMessage());
            return ui.getOutput();
        }
    }

    /**
     * Returns boolean indicating if exit command was received.
     */
    public boolean getExit() {
        return isExit;
    }
}
