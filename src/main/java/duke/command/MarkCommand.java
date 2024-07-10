package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.*;

/**
 * Marks a task as either done or not done
 */
public class MarkCommand extends Command {
    protected String response;
    public MarkCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        boolean taskDone = response.split(" ")[0].equals("mark") ? true : false;
        int taskIndex = Integer.parseInt(response.split(" ")[1]) - 1;
        taskList.setTaskDone(taskIndex, taskDone);
        return taskList.toString();
    }
}
