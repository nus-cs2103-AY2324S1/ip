package banterbot;

import banterbot.command.Command;
import banterbot.exception.DukeException;
import banterbot.helper.Parser;
import banterbot.helper.Storage;
import banterbot.helper.Ui;
import banterbot.task.TaskList;

/**
 * Represents the ChatBot the User interacts with.
 */
public class BanterBot {
    /** A Storage that handles the reading and writing of local files. */
    private Storage storage;
    /** The list of Tasks a User has. */
    private TaskList tasks;
    /** The User Interface the ChatBot uses to interact with the User. */
    private Ui ui;

    /**
     * Constructs a BanterBot.BanterBot with a filePath leading to the local .txt file.
     * @param filePath
     */
    public BanterBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the ChatBot and allows the User to start interacting with it.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.print(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BanterBot("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
