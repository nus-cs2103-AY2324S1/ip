package Duke;

import Commands.Command;
import Commands.ExitCommand;
import OOP.Parser;
import OOP.Storage;
import OOP.TaskList;
import OOP.Ui;

public class Duke {
    /** The name of the chatbot */
    private String name = "WallE";

    /** The Storage instance to be used by Duke */
    private Storage storage;

    /** The TaskList instance to be used by Duke to keep track of tasks */
    private TaskList tasks;
    /** The Ui instance that handles interactions with the user */
    private Ui ui;

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
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Greets the user and runs the chatbot.
     */
    public void run() {
        ui.printGreeting(this.name);
        runCommandLoopUntilExitCommand();
    }

    /**
     * Reads the user command and executes it, until the user issues the bye (exit) command.
     * */
    private void runCommandLoopUntilExitCommand() {
        Command command = null;
        do {
            try {
                String userCommandText = ui.getUserCommand();
                ui.printDivider();
                command = Parser.parseCommand(userCommandText);
                command.execute(tasks, ui, storage);
                storage.save(tasks);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.printDivider();
            }
        } while (!ExitCommand.isExit(command));
    }
}
