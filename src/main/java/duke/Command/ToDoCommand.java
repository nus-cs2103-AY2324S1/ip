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

    public ToDoCommand(String input) {
        this.input = input;
    }
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
