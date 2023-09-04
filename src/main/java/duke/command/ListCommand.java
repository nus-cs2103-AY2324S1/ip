package duke.command;

import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {

    }
    String commandMessage = "";
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }
    @Override
    public void execute(TaskList taskList) {
        if (taskList.getLength() == 0) {
            commandMessage = "Wow! You have no tasks!";
        } else {
            commandMessage = "Here are your tasks: " + "\n" + taskList.toString();
        }
    }
}
