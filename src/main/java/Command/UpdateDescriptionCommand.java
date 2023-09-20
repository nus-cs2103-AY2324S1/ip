package command;

import exception.DukeException;
import main.Storage;
import main.UI;
import task.TaskList;

import static java.lang.Integer.parseInt;

public class UpdateDescriptionCommand extends Command {

    private String updatedDescription;
    private int taskIndex;

    public UpdateDescriptionCommand(String command) {
        String shortCommand = command.substring(18).trim();
        this.taskIndex = parseInt(shortCommand, 0, 1, 10);

        String[] list = shortCommand.split("/");

        this.updatedDescription = list[1].trim().substring(7);

    }

    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        String message = taskList.updateTaskDescription(taskIndex, this.updatedDescription);
        storage.saveList(taskList);
        return message;
    }
}
