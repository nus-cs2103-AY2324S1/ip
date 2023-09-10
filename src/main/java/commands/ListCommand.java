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
    public void execute() {
        for (int i=0; i<taskList.size(); i++) {
            String message = String.format("%d. %s", i+1, taskList.get(i).getTaskAsString());
            System.out.println(message);
        };
    }
}
