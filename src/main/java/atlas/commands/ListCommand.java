package atlas.commands;

import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;


/**
 * Command to print the contents of the task list
 */
public class ListCommand extends MultiTaskCommand {
    private static final String OUTPUT_HEADER_MESSAGE = "Here are your tasks:";

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null;
        List<Task> tasks = taskList.getTasks();
        return generateListOutput(tasks, OUTPUT_HEADER_MESSAGE);
    }
}
