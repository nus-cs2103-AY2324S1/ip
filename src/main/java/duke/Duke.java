package duke;

import duke.command.Command;

/**
 * A chatbot that helps you keep track of tasks.
 */
public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructor for a duke.Duke instance.
     *
     * @param filePath The location of the save file to be created at and read from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response of parsing and executing the user input command.
     *
     * @param input The String of the user input command.
     * @return      The response message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                return Ui.showExitMessage();
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
