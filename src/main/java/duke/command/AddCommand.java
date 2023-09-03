package duke.command;
import java.io.IOException;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.Deadline;
import duke.task.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * AddCommand used to add the tasks to the Tasklist class via execute method
 * @author tangiansen
 * @version 0.1
 * @since 2023-08-30
 */

public class AddCommand extends Command {

    private Task task;
    /**
     * @param command takes in the format ie. "todo cry"
     * @throws DukeException throws a DukeException which indicates no description if no text description
     */
    public AddCommand(String command) throws DukeException {
        try {
            int firstIndexSpace = command.indexOf(" ");
            String taskType = command.substring(0, firstIndexSpace);
            String taskName = command.substring(firstIndexSpace + 1);

            if (taskType.equals("event")) {
                task = new Event(taskName.trim());
            } else if (taskType.equals("deadline")) {
                task = new Deadline(taskName.trim());
            } else if (taskType.equals("todo")) {
                task = new Todo(taskName.trim());
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" No Description given!");
        }

    }

    /**
     * Executes the AddCommand to add task to TaskList and store to local storage
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference
     * @throws DukeException throws a DukeException which indicates no description if no text description
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.addTask(task);
            store.save(tasks);
            ui.printAddTask(tasks.size(), task);

        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }

    }
}
