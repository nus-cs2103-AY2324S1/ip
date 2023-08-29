package ducky.command;

import ducky.DuckyException;
import ducky.Storage;
import ducky.TaskList;
import ducky.UserInterface;

public abstract class Command {

    public boolean isExit() {
        return false;
    }
    public abstract void execute(TaskList taskList, UserInterface ui, Storage storage) throws DuckyException;
}
