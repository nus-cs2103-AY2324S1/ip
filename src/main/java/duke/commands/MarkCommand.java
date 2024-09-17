package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents Mark/Unmark Command to be executed
 */
public class MarkCommand extends Command {
    String input;

    public MarkCommand(String input, TaskList taskList) {
        super(taskList);
        this.input = input;
    }

    /**
     * mark or unmark an existing task
     *
     * @return response String
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String[] splitStr = input.trim().split("\\s+");
        inputChecker(splitStr, "mark/unmark");
        int index = Integer.parseInt(splitStr[1]);
        Task item = taskList.getOneTask(index - 1);
        return item.setAction(splitStr[0]);
    }
}
