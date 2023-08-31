package commands;

import storage.DataFile;
import tasks.TaskList;

public class ByeCommand extends Command{

    @Override
    public void execute(TaskList tasks, DataFile dF) {
        System.out.println(this);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Bye. Hope to see you again soon!";
    }
}
