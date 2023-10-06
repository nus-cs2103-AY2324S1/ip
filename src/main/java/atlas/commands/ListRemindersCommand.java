package atlas.commands;

import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;


/**
 * Command to return list of reminders
 */
public class ListRemindersCommand extends MultiTaskCommand {
    private static final String OUTPUT_HEADER_MESSAGE = "Time comes for us all, mortal, especially you. "
            + "These labours are due soon:";

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null;
        List<Task> tasks = taskList.getReminders();
        return generateListOutput(tasks, OUTPUT_HEADER_MESSAGE);
    }
}
