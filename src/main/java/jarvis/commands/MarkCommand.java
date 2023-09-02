package jarvis.commands;

import jarvis.Storage;
import jarvis.Ui;
import jarvis.exceptions.InvalidIndexException;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

public class MarkCommand implements Command {

    private String userInput;

    public MarkCommand(String userInput) {
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
            task.markCompleted();
            storage.saveTasks(taskList.getTaskList());
            ui.printTaskStatus(task);
        } else {
            throw new InvalidIndexException(null);
        }
    }
}
