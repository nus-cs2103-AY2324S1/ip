package jarvis.commands;

import jarvis.Storage;
import jarvis.Task;
import jarvis.TaskList;
import jarvis.Ui;
import jarvis.exceptions.InvalidIndexException;

public class UnmarkCommand implements Command{
      private String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException {
        String[] userInputSpilt = userInput.split(" ");
        int index = Integer.parseInt(userInputSpilt[1]);

        if (userInput.length() <= 4) {
            ui.printResponse("Master, please indicate which task you wish to mark DONE?\n" + 
                    "from 1 to " + taskList.getTaskCount());
        } else if (index >= 1 && index <= taskList.getTaskCount()) {
            Task task = taskList.getTask(index - 1);
            task.unmarkCompleted();
            storage.saveTasks(taskList.getTaskList());
            ui.printTaskStatus(task);
        } else {
            throw new InvalidIndexException(null);
        }
    }
}
