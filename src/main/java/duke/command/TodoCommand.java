package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a todo task to Duke's task list.
 */
public class TodoCommand extends Command {

    private String description;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the deadline task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTodoMessage(taskList.addTodo(this.description), taskList.getSize());
    }
}
