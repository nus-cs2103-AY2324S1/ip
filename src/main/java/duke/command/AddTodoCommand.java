package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private String description;

    /**
     * Constructs an AddTodoCommand with the provided description.
     *
     * @param description The description of the deadline task.
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (description.trim().isEmpty()) {
                throw new EmptyDescriptionException("todo");
            }

            Todo newTodo = new Todo(description);
            taskList.addTask(newTodo);
            return ui.showAdd(newTodo, taskList.getLength());
        } catch (EmptyDescriptionException e) {
            return ui.showDukeException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
