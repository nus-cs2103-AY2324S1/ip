package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class that handles the todo command.
 */
public class ToDoCommand extends Command {
    private String input;
    /**
     * Constructor for todo command.
     * @param input input passed in.
     */
    public ToDoCommand(String input) {
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
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        if (input.trim().length() <= 4) {
            throw new DukeException("Sorry! The description of a todo cannot be empty :(");
        }
        Task task = new ToDo(input.substring(5));
        tasks.addTask(task);
        storage.writeTasksToFile(tasks);
        return ui.printAddTaskToList(tasks, task);
    }

}
