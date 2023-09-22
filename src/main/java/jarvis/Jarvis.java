package jarvis;

import jarvis.commands.Command;
import jarvis.exceptions.JarvisException;
import jarvis.gui.Ui;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.tasks.TaskList;

/**
 * The main class for Jarvis application, a CLI chat-bot.
 * It initialises and manages the core components of the application.
 * Namely: ui, storage, taskList, and commands.
 */
public class Jarvis {

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructor for Jarvis class.
     */
    public Jarvis() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage();
        taskList.setTasks(storage.loadTasks());
    }

    /**
     * Responds to user input by parsing and executing commands.
     *
     * @param userInput The input provided by the user through CLI.
     */
    public String respond(final String userInput) {
        try {
            assert userInput != null : "Command/User Input cannot be null";
            Command command = Parser.parseCommand(userInput);
            taskList.setTasks(storage.loadTasks());
            return command.execute(taskList, ui, storage);
        } catch (JarvisException e) {
            return ui.printError(e.getMessage());
        }
    }
}

