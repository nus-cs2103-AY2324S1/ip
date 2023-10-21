package command;

import duke.Storage;
import duke.TaskList;
import exceptions.DukeException;

public class UpdateCommand extends Command {
    private int index;
    private String editType;
    private String edit;

    /**
     * Constructs an UpdateCommand object with the string containing the fields.
     *
     * @param response The string containing relevant field to be updated.
     */
    public UpdateCommand(String response) {
        super(false);
        String[] parts = response.split(" ", 3);
        assert parts.length == 3;
        this.index = Integer.parseInt(parts[0]) - 1;
        this.editType = parts[1];
        this.edit = parts[2].substring(1);
    }

    /**
     * Executes the update command, updating the relevant field in the the command.
     *
     * @param taskList The task list to operate on.
     * @param storage  The storage handler for reading/writing tasks.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        switch (editType) {
            case "title":
                taskList.getTask(index).editTitle(edit);
                storage.writeListToFile(taskList);
                return "Task title successfully updated cowboy!";
            case "deadline":
                taskList.getTask(index).editDeadline(edit);
                storage.writeListToFile(taskList);
                return "Task deadline successfully updated cowboy!";
            case "start":
                taskList.getTask(index).editStart(edit);
                storage.writeListToFile(taskList);
                return "Task start time successfully updated cowboy!";
            case "end":
                taskList.getTask(index).editEnd(edit);
                storage.writeListToFile(taskList);
                return "Task end time successfully updated cowboy!";
            default:
                return "Please check your inputs again or I'll punish you!";
        }

    }
}
