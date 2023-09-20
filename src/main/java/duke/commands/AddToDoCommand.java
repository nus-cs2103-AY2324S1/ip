package duke.commands;

import java.io.IOException;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.task.Todo;
import duke.storage.Storage;

/**
 * Represents a command for adding a todo task
 * to TaskList, writing it to a file, and displaying a task added message.
 */
public class AddToDoCommand extends Command {
    /** Description of the Todo task */
    private final String description;

    /**
     * Constructor to initialize AddToDoCommand.
     *
     * @param description Description of the todo task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws IOException {
        Todo newTodo = new Todo(description);
        taskList.addTask(newTodo);
        Storage.save(newTodo);
        assert taskList.countTasks() >= 0 : "Invalid task list size";
        return message.showTaskAdded(newTodo, taskList.countTasks());
    }
}
