package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Main entry point of the bot
 */
public class Duke {

    /**
     * User Interface for generating chats.
     */
    private final Ui ui = new Ui();

    /**
     * duke.task.Task list to store the tasks.
     */
    private final TaskList tasks = new TaskList();

    /**
     * The main entry point of the application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Continuously reads user input,
     * parses it into commands, and executes the commands
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Storage.readTask(tasks, ui);

        while (!isExit) {
            try {
                String userInput = ui.readInput();
                ui.showLine();
                Command command = Parser.parse(userInput);
                isExit = command.execute(this.tasks, ui);
                Storage.writeTask(tasks, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
