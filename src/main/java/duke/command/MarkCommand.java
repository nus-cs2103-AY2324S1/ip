package duke.command;

import java.util.stream.Stream;

import duke.core.DukeException;
import duke.core.Storage;
import duke.core.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command to mark a task as done.
 */
public class MarkCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("There are no tasks added. Please add a task first.");
        }

        if (!super.parameterMap.containsKey("default")) {
            throw new DukeException("Please enter a task number.");
        }

        String taskIndexString = super.parameterMap.get("default");
        try {
            int taskIndex = Integer.parseInt(taskIndexString) - 1;

            if (taskIndex < 0) {
                    throw new DukeException("Task number cannot be negative.\n     "
                                            + "Please retry with a valid task number.");
            }

            if (taskIndex >= tasks.size()) {
                    throw new DukeException(String.format("Task %d does not exist. Use a number between 1 and %d.",
                                                          taskIndex + 1,
                                                          tasks.size()));
            }

            Task taskMarked = tasks.markAsDone(taskIndex);
    
            if (taskMarked == null) {
                return;
            }
    
            Ui.respond(Stream.of("Nice! I've marked this task as done:",
                                       String.format("  %s",taskMarked.toString())));
            tasks.storeTasks();
        }   catch (NumberFormatException e) {
                throw new DukeException(String.format("Task number provided \"%s\" is not a number.\n     "
                                                      + "Please retry with a valid task number.", taskIndexString));
        }
    }
}
