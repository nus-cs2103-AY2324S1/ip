package duke.command;

import duke.exception.KoraException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
    private String keyword;
    private TaskList newTaskList = new TaskList();

    public FindCommand(String[] details) {
        keyword = details[0].replace("find ", "");
    }

    @Override
    public String getCommandMessage() {
        if (newTaskList.getLength() == 0) {
            return "There are no matching task!";
        } else {
            return "Here are your matching tasks in your list: \n" + newTaskList.toString();
        }
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
        int taskListSize = taskList.getLength();
        for (int i = 0; i < taskListSize; i++) {
            Task task = taskList.getTask(i + 1);
            if (task.getDetails().contains(keyword)) {
                newTaskList.addTask(task);
            }
        }
    }
}
