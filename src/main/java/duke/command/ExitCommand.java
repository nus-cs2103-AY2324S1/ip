package duke.command;

import duke.task.TaskStorage;

/**
 * Represents a command to exit the program
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskStorage taskStorage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
