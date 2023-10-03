package duke.commands;

import duke.TaskListStorage;

/**
 * Represents a command that lists all the tasks in the task list.
 */
public class ListCommand implements Command {

    @Override
    public String execute(TaskListStorage taskListStorage) {
        return taskListStorage.printList();
    }
}
