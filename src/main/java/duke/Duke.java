package duke;

import commands.Command;
import gui.Launcher;
import oop.Parser;
import oop.Storage;
import oop.TaskList;
import oop.Ui;

/**
 * The main class of the whole application.
 */
public class Duke {
    /**
     * The name of the chatbot.
     */
    private String name = "WallE";

    /**
     * The Storage instance to be used by duke
     */
    private Storage storage;

    /**
     * The TaskList instance to be used by duke to keep track of tasks
     */
    private TaskList tasks;
    /**
     * The Ui instance that handles interactions with the user
     */
    private Ui ui;

    /**
     * Default constructor for duke, with no arguments
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt"); // Provide a default filepath
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.getLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs an instance of duke which is used to run the chatbot.
     *
     * @param filePath The filepath of the storage file to be used by duke when loading and saving tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.getLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Returns a String containing the response of wallE when given an input from the user.
     *
     * @param input The user's text input.
     * @return The String to be displayed to the user in response to their input.
     */
    public String getResponse(String input) {
        Command command;
        String responseString;
        try {
            command = Parser.parseCommand(input);
            // commands return messageStrings so that they can be returned here and displayed.
            responseString = command.execute(tasks, ui, storage);
            storage.save(tasks);
        } catch (DukeException e) {
            responseString = ui.getErrorMessage(e.getMessage());
        }
        return responseString;
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Greets the user and runs the chatbot.
     */
    public void run() {
        Launcher.main(new String[0]);
    }
}
