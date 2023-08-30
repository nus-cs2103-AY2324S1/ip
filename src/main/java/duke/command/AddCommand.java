package duke.command;
import duke.task.*;
import duke.helper.*;
import java.io.IOException;

/**
 * AddCommand to add the tasks to the tasklist
 * @version 0.1
 * @since 2023-08-29
 */

public class AddCommand extends Command{

    Task task;
    /**
     *
     * @param command takes in the format ie. "todo cry"
     * @throws DukeException throws a DukeException which indicates no desc if no text left
     */
    public AddCommand(String command) throws DukeException {
        try {
            int firstIndexSpace = command.indexOf(" ");
            String taskType = command.substring(0,firstIndexSpace);
            String taskName = command.substring(firstIndexSpace + 1);

            if (taskType.equals("event")) task = new Event(taskName.trim());
            else if (taskType.equals("deadline")) task = new Deadline(taskName.trim());
            else if (taskType.equals("todo")) task = new Todo(taskName.trim());
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" No Description given!");
        }

    }

    /**
     * Executes the AddCommand to add task to TaskList using the parameters
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference
     * @throws DukeException throws a DukeException which indicates no desc if no text left
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.addTask(task);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }

    }
}
