package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Represents List command to be executed
 */
public class ListCommand extends Command {
    public ListCommand(TaskList taskList) {
        super(taskList);
    }

    /**
     * List out tasks in taskList
     *
     * @return response string
     * @throws DukeException
     */
    @Override
    public String execute() throws DukeException {
        String reply = "";
        reply += "  Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.listSize(); i++) {
            int index = i + 1;
            reply += "  " + index + "." + taskList.getOneTask(i).toString() + "\n";
        }
        return reply;
    }
}
