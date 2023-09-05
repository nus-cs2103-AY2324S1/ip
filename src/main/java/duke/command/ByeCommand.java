package duke.command;

import duke.task.TaskList;

public class ByeCommand extends Command {
    private String commandMessage = "";
    public ByeCommand() {
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList) {

        commandMessage = "Byebye. See you again!";
    }

    @Override
    public boolean isExitYet() {
        return true;
    }
}
