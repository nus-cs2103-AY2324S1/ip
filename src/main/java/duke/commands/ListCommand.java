package duke.commands;

import duke.TaskListStorage;

public class ListCommand implements Command {

    @Override
    public void execute(TaskListStorage taskListStorage) {
        taskListStorage.printList();
    }
}
