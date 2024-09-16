package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Overall Duke class encapsulating the duke bot, Chatty.
 * @author Goh Ler Xuan
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke bot.
     * @param filePath Path to data file with saved tasks from user's previous sessions
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Parses input commands and returns corresponding responses.
     *
     * @param input command
     * @return response to input command
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
