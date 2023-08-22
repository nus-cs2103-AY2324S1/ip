package command;

import task.TaskList;

public class ByeCommand extends Command {
    public ByeCommand() {

    }
    String commandMessage = "";
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList, int taskIndex) {
        commandMessage = "Byebye. See you again!";
    }
}
