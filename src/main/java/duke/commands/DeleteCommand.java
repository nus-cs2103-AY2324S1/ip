package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents Delete Command to be executed
 */
public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input, TaskList taskList) {
        super(taskList);
        this.input = input;
    }

    /**
     * Deletes specified task from taskList
     *
     * @return response string
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String[] splitStr = input.trim().split("\\s+");
        inputChecker(splitStr, "delete");
        int index = Integer.parseInt(splitStr[1]);
        Task item = taskList.removeTask(index - 1);
        return item.delete();
    }
}
