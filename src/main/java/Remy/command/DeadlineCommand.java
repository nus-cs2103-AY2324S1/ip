package Remy.command;

import Remy.task.TaskList;
import Remy.Ui;
import Remy.Storage;
import Remy.ChatbotException;
import Remy.task.Deadline;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private String taskName;
    private String dueDate;
    public static final String COMMAND_WORD = "deadline";

    public DeadlineCommand(String input) throws ChatbotException {
        if (input.length() < 10) throw new ChatbotException("missing info lah.");
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length == 2) {
            this.taskName = parts[0];
            this.dueDate = parts[1];
        } else {
            throw new ChatbotException("missing info lah.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        try {
            Deadline temp = new Deadline(this.taskName, this.dueDate);
            taskList.add(temp);
            storage.save(taskList);
            Ui.printAddedTask(temp, taskList.size());
        } catch (DateTimeParseException e) {
            throw new ChatbotException("You don't know how to write the time isit?: " + e.getMessage());
        }
    }
}
