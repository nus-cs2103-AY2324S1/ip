package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Class that handles the deadline command.
 */
public class DeadlineCommand extends Command {
    private String input;
    public DeadlineCommand(String input) {
        this.input = input;
    }
    /**
     * Executes the command.
     * @param ui the ui class used.
     * @param storage the storage that is used.
     * @param tasks the tasklist that is used.
     * @return String representation of the execution.
     * @throws IOException IOException
     * @throws DukeException DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws IOException, DukeException {
        if (input.trim().length() <= 8) {
            throw new DukeException("Sorry! The description of a deadline cannot be empty :(");
        }
        if (!input.contains("/by")) {
            throw new DukeException("Hey bud! Please include when the deadline is! "
                    + "\nFor example you can type: deadline read /by 2023-09-01 1700");
        }
        int index = input.lastIndexOf("/by");
        if (input.substring(9, index).isEmpty()) {
            throw new DukeException("Sorry! The description of a deadline cannot be empty :(");
        }

        Task task = new Deadline(input.substring(9, index - 1), input.substring(index + 4));
        tasks.addTask(task);
        storage.writeTasksToFile(tasks);
        return ui.printAddTaskToList(tasks, task);
    }

}
