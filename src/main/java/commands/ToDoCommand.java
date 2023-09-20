package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Task;
import duke.ToDo;

import java.io.IOException;

/**
 * ToDoCommand class for the command that asks to create a new todo.
 */
public class ToDoCommand extends Command {

    private Task toDoToAdd;

    public ToDoCommand(String taskInfo) {
        Task todo = new ToDo(taskInfo, false);
        this.toDoToAdd = todo;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(this.toDoToAdd);
        storage.appendToFile("T | " + this.toDoToAdd.getStatusIcon() + " | " +
                this.toDoToAdd.getTaskDescription()+ System.lineSeparator());
        return ui.printAddedTask(tasks.getSize(), this.toDoToAdd);
    }
}
