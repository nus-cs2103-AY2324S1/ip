package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.ui.Ui;
import duke.tasks.ToDos;

/**
 * A command to create a ToDo task.
 */
public class TodoCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected String input;

    /**
     * Constructor for the todo command
     *
     * @param input The input for the command
     * @param taskList The taskList to store the task
     * @param storage The storage system to store the list
     * @param ui The ui to print the commands
     */
    public TodoCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the command
     *
     * @throws DukeException when theres an error saving the file
     */
    @Override
    public String execute() throws DukeException {
        ToDos task = new ToDos(input);
        taskList.addTask(task);
        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
        return ui.printAddTask(taskList, task);
    }
}
