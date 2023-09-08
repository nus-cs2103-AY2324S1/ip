package Duke;

import Commands.Command;
import GUI.Launcher;
import OOP.Parser;
import OOP.Storage;
import OOP.TaskList;
import OOP.Ui;
public class Duke {
    /**
     * The name of the chatbot
     */
    private String name = "WallE";

    /**
     * The Storage instance to be used by Duke
     */
    private Storage storage;

    /**
     * The TaskList instance to be used by Duke to keep track of tasks
     */
    private TaskList tasks;
    /**
     * The Ui instance that handles interactions with the user
     */
    private Ui ui;

    /**
     * Default constructor for Duke, with no arguments
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt"); // Provide a default filepath
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs an instance of Duke which is used to run the chatbot
     *
     * @param filePath The filepath of the storage file to be used by duke when loading and saving tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns a String containing the response of wallE when given an input from the user.
     *
     * @param input
     * @return The String to be displayed to the user in response to their input.
     */
    public String getResponse(String input) {
        Command command;
        String responseString;
        try {
            // ui.printDivider();
            command = Parser.parseCommand(input);
            // need commands to return messageStrings so that they can be returned here and displayed.
            responseString = command.execute(tasks, ui, storage);
            storage.save(tasks);
        } catch (DukeException e) {
            responseString = ui.showError(e.getMessage());
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
        // ui.printGreeting(this.name);
        // runCommandLoopUntilExitCommand();
        Launcher.main(new String[0]);
    }
}