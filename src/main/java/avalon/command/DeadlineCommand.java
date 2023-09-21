package avalon.command;

import avalon.AvalonException;
import avalon.task.Deadline;
import avalon.utility.Storage;
import avalon.task.TaskList;
import avalon.utility.Ui;

public class DeadlineCommand extends Command {

    private final String userInput;

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) throws AvalonException {
        String[] parts = userInput.substring(9).split(" /by ");
        if (parts.length != 2) {
            throw new AvalonException("Please provide a description and a deadline "
                    + "(use /by).");
        }
        String description = parts[0];
        String by = parts[1];

        Deadline deadline = new Deadline(description, by);
        taskList.addTask(deadline);
        ui.showAddTaskMessage(taskList);
        return ui.getOutput();
    }
}
