package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return Ui.listTasks(tasks);
    }
}
