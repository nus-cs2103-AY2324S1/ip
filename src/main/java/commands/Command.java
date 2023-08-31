package commands;

import storage.DataFile;
import tasks.TaskList;

abstract public class Command {

    abstract public void execute(TaskList tasks , DataFile dF);

    public boolean isExit() {
        return false;
    }
}
