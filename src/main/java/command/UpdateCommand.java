package command;

import duke.Storage;
import duke.TaskList;
import exceptions.DukeException;

public class UpdateCommand extends Command {
    private int index;
    private String editType;
    private String edit;

    public UpdateCommand(String response) {
        super(false);
        String[] parts = response.split(" ", 3);
        assert parts.length == 3;
        this.index = Integer.parseInt(parts[0]) - 1;
        this.editType = parts[1];
        this.edit = parts[2].substring(1);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        switch (editType) {
            case "title":
                taskList.getTask(index).editTitle(edit);
                storage.writeListToFile(taskList);
                return "Task title successfully updated";
            case "deadline":
                taskList.getTask(index).editDeadline(edit);
                storage.writeListToFile(taskList);
                return "Task deadline successfully updated";
            case "start":
                taskList.getTask(index).editStart(edit);
                storage.writeListToFile(taskList);
                return "Task start time successfully updated";
            case "end":
                taskList.getTask(index).editEnd(edit);
                storage.writeListToFile(taskList);
                return "Task end time successfully updated";
            default:
                return "Please check your inputs again!";
        }

    }
}
