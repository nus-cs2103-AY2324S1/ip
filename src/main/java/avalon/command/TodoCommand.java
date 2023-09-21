package avalon.command;

import avalon.AvalonException;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.task.ToDo;
import avalon.utility.Ui;

public class TodoCommand extends Command {

    private final String userInput;

    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            throw new AvalonException("The description of a todo cannot be empty.");
        }

        ToDo todo = new ToDo(description);
        taskList.addTask(todo);
        ui.showAddTaskMessage(taskList);
        return ui.getOutput();
    }
}
