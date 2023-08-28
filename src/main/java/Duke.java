import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main entry point of the bot
 */
public class Duke {

    /**
     * User Interface for generating chats.
     */
    private Ui ui = new Ui();

    /**
     * Task list to store the tasks.
     */
    private TaskList tasks = new TaskList();

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
        FileManager.readTask(tasks, ui);

        while (!isExit) {
            try {
                String userInput = ui.readInput();
                ui.showLine();
                Command command = Parser.parse(userInput);
                isExit = command.execute(this.tasks, ui);
                FileManager.writeTask(tasks,ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
