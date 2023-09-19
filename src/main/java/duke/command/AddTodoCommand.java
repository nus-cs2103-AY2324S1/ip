package duke.command;

import java.io.IOException;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Todo;

/**
 * The AddTodoCommand class.
 */
public class AddTodoCommand extends Command {
    private String taskName;

    /**
     * Constructor for the duke.command.AddTodoCommand class.
     *
     * @param taskName
     */
    public AddTodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Executes the command by creating a todo, adding it to the taskList,
     * adding it to the storage and displaying a message to the ui.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Todo todo = new Todo(taskName, false);
            taskList.addTask(todo);
            storage.rewriteToFile(taskList.getList());
            return ui.successfulAddTaskMsg(todo.userDisplayString(), taskList.getIndex());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
