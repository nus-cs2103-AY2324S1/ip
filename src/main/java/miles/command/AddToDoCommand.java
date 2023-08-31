package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;
import miles.task.ToDo;

public class AddToDoCommand extends Command {
    private String input;

    public AddToDoCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ToDo newToDo = new ToDo(input);
        storage.saveWhenAddTask(newToDo, taskList);
        int n = taskList.getSize();
        ui.printAddedTask(newToDo, n);
    }
}
