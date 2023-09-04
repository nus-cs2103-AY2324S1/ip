package command;

import exception.KoraException;
import task.Task;
import task.TaskList;

public abstract class Command {
    public static String line = "------------------------------";
    public Command() {

    }

    public void printOutput(String output) {
        System.out.println(line + "\n" + output + "\n" +line);
    }
    public abstract String getCommandMessage();
    public abstract void execute(TaskList taskList) throws KoraException;

    public boolean isExitYet() {
        return false;
    }
}
