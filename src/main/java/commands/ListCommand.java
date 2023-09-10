package commands;

import functions.Storage;
import functions.TaskList;
import tasks.Task;

public class ListCommand extends Command{
    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        String message = "";
        for (int i=0; i<taskList.size(); i++) {
            message += String.format("%d. %s", i+1, taskList.get(i).getTaskAsString());
            message += "\n";
        };
        return message;
    }
}
