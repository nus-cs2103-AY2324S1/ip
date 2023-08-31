package commands;

import storage.DataFile;
import tasks.TaskList;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList tasks, DataFile dF) {
        System.out.println(this);
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + "." + tasks.getTask(i));
        }
    }

    @Override
    public String toString() {
        return "Here are the tasks in your list:";
    }
}
