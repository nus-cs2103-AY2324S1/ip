package dude;

import dude.command.DudeCommand;
import dude.exception.DudeException;
import dude.task.TaskList;

/**
 * Dude (Duke, but renamed).
 */
public class Dude {
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Dude.
     *
     * @param filePath Path to save file on disk.
     */
    public Dude(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DudeException e) {
            ui.printMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Reads user input and returns response to it.
     *
     * @param input User input.
     * @return String response to user input.
     */
    public String getResponse(String input) {
        try {
            DudeCommand c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DudeException e) {
            return e.getMessage();
        }
    }
}
