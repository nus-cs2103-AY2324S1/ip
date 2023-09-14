package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskStorage;

/**
 * The {@code AppController} class is the main logic controller of the application.
 */
class AppController {
    private final TaskStorage taskStorage;

    public AppController() {
        this.taskStorage = new TaskStorage();
    }

    public void runCliController() {
        Scanner scanner = new Scanner(System.in);
        CliOutputUi outputUI = new CliOutputUi();
        boolean isExit = false;

        while (!isExit) {
            String input = scanner.nextLine();
            Command command = CommandParser.parse(input);
            if (command.isExit()) {
                isExit = true;
            }
            outputUI.echo(command.execute(taskStorage));
        }
    }
}
