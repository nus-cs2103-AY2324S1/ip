package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskStorage;

/**
 * The {@code AppController} class is the main logic controller of the application.
 */
class AppController {
    private boolean isExit = false;
    private final Scanner scanner = new Scanner(System.in);
    private final TaskStorage taskStorage;
    private final CliOutputUi outputUI;

    public AppController() {
        this.outputUI = new CliOutputUi();
        this.taskStorage = new TaskStorage();
    }

    public void run() {
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
