package duke.command;

import duke.DukeException;

import duke.task.Task;
import duke.task.TaskList;

import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks) {
        if (this.description.isEmpty()) {
            throw new DukeException("So what exactly do you want to find?");
        }

        ArrayList<Task> list = tasks.findTasks(description);

        return Ui.findTasks(list);
    }
}