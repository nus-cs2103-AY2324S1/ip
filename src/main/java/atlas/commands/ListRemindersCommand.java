package atlas.commands;

import java.util.List;

import atlas.components.Storage;
import atlas.components.TaskList;
import atlas.tasks.Task;


/**
 * Command to return list of reminders
 */
public class ListRemindersCommand extends MultiTaskCommand {
    private static final String OUTPUT_HEADER_MESSAGE = "Here are your reminders:";

    @Override
    public String execute(TaskList taskList, Storage storage) {
        assert taskList != null;
        List<Task> tasks = taskList.getReminders();
        return generateListOutput(tasks, OUTPUT_HEADER_MESSAGE);
    }
}
