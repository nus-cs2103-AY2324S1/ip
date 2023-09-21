package avalon.command;

import avalon.AvalonException;
import avalon.task.TaskList;
import avalon.utility.Storage;
import avalon.utility.Ui;

public class PriorityCommand extends Command{

    private final String userInput;

    public PriorityCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        int taskIndex = Integer.parseInt(userInput.substring(9, 10)) - 1;
        int priority = Integer.parseInt(userInput.substring(12));
        boolean isValidIndex = taskIndex >= 0 && taskIndex < taskList.size();
        boolean isValidPriority = priority >= 0 && priority <= 9;

        if (!isValidIndex){
            throw new AvalonException("Task number does not exist.");
        }

        if (!isValidPriority) {
            throw new AvalonException("Please enter a number between 0 - 9.");
        }

        taskList.get(taskIndex).setPriority(priority);
        ui.showPriorityMessage(taskList, taskIndex);
        return ui.getOutput();
    }
}
