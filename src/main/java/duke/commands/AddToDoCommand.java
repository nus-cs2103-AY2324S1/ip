package duke.commands;

import java.io.IOException;

import duke.data.TaskList;
import duke.data.task.Todo;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The AddToDoCommand adds a todo into TaskList, writes into .txt file
 * and display a task added message when it is executed.
 */
public class AddToDoCommand extends Command {
    /** Description of the Todo task */
    private final String description;

    /**
     * Constructor to initialize AddToDoCommand.
     *
     * @param description Description of the Todo task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Todo newTodo = new Todo(description);
        taskList.addTask(newTodo);
        Storage.save(newTodo);
        ui.showTaskAdded(newTodo, taskList.countTasks());
    }
}
