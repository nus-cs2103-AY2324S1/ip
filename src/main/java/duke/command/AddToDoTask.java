package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ToDo;

/** Abstraction of a command to add a task to a list. */
public class AddToDoTask extends AddTask {

    /**
     * Creates a add todo task command.
     *
     * @param list List for the todo task to be added.
     * @param specifications Description of the todo task.
     */
    public AddToDoTask(TaskList list, String specifications) {
        super(list, specifications);
    }

    @Override
    public String execute() {
        if (specifications.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task toDoTask = new ToDo(specifications, false);
        this.list.store(toDoTask);
        return this.ui.showTaskAdded(toDoTask, this.list.length());
    }
}
