package commands;

import duke.TaskListStorage;

public interface Command {
    void execute(TaskListStorage taskListStorage) throws Exception;
}