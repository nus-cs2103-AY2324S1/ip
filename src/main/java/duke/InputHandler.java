package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.task.TaskStorage;

class InputHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final CliOutputUi outputUI;
    private final TaskStorage taskStorage;

    private boolean isExit = false;

    public InputHandler(CliOutputUi outputUI, TaskStorage taskStorage) {
        this.outputUI = outputUI;
        this.taskStorage = taskStorage;
    }

    public void handleInput() {
        String input = scanner.nextLine();
        Command command = CommandParser.parse(input);
        if (command.isExit()) {
            isExit = true;
        }
        outputUI.echo(command.execute(taskStorage));
    }

    public boolean isExit() {
        return isExit;
    }
}
