package duke.command;

import java.util.stream.Stream;

import duke.core.Storage;
import duke.core.Ui;
import duke.task.TaskList;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            Ui.respond("There are no tasks to list.");
            return;
        }

        Stream<String> taskDetails = tasks.getTasks().map(task -> task.toString());
        Ui.respond(taskDetails);
    }
}
