package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskStorage;

/**
 * The {@code AppController} class is the main logic controller of the application.
 */
public class AppController {
    private final TaskStorage taskStorage;
    private boolean isExit = false;

    public AppController() {
        this.taskStorage = new TaskStorage();
    }

    /**
     * Runs the CLI version of the application.
     */
    public void runCliController() {
        Scanner scanner = new Scanner(System.in);
        CliOutputUi outputUI = new CliOutputUi();

        while (!isExit) {
            String input = scanner.nextLine();
            Command command = CommandParser.parse(input);
            if (command.isExit()) {
                isExit = true;
            }
            outputUI.echo(command.execute(taskStorage));
        }
    }

    /**
     * Runs the GUI version of the application.
     * @param input The input from the user.
     * @return The response from the application.
     */
    public String getResponse(String input) {
        Command command = CommandParser.parse(input);
        if (command.isExit()) {
            isExit = true;
        }
        return command.execute(taskStorage);
    }

    /**
     * Returns the exit status of the application.
     * @return The exit status of the application.
     */
    public boolean isExit() {
        return isExit;
    }

    public static String getWelcomeMessage() {
        return "Hello! I'm Meowies\n    What can I do for you?";
    }
}
