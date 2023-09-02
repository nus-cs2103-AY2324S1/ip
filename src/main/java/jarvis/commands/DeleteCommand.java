package jarvis.commands;

import jarvis.Storage;
import jarvis.Task;
import jarvis.TaskList;
import jarvis.Ui;
import jarvis.exceptions.InvalidIndexException;

public class DeleteCommand implements Command {

    private String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        String[] userInputSpilt = userInput.split(" ");
        int index = Integer.parseInt(userInputSpilt[1]);

        if (index >= 1 && index <= taskList.getTaskCount()) {
            Task removedTask = taskList.deleteTask(index - 1);
            storage.saveTasks(taskList.getTaskList());
            ui.printResponse("Noted Master! I've removed this task:\n" + "\t" + removedTask.toString() + "\n" +
                    "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
        } else {
            throw new InvalidIndexException("    Please indicate which task you wish to DELETE?\n" + 
                    "    From 1 to " + taskList.getTaskCount() + "\n" +
                    "    Current Index: " + index);
        }
    }
}
