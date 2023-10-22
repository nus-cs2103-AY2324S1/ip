package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents Tag command to be executed
 */
public class TagCommand extends Command {
    private String input;

    public TagCommand(String input, TaskList taskList) {
        super(taskList);
        this.input = input;
    }

    /**
     * tag a task by its index in the taskList
     *
     * @return response string
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String[] splitStr = input.trim().split("\\s+");
        inputChecker(splitStr, "tag");
        Task item = taskList.getOneTask(Integer.parseInt(splitStr[1]) - 1);
        String[] getTag = input.split("#");
        inputChecker(getTag, "tag");
        return item.setTag(getTag[1]);
    }
}
