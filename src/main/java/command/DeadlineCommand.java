package command;

import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.EmptyDateTimeException;
import exception.EmptyInputException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;
import task.Deadline;

/**
 * The Command to indicate that the user wishes to add a deadline to the task list.
 */
public class DeadlineCommand extends Command {

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException,
            InvalidFormatException, EmptyDateTimeException, InvalidDateTimeException {
        String input = ui.getInput();
        try {
            if (input.split(" ").length < 2) {
                throw new EmptyInputException("a deadline");
            } else if (!input.contains("/by")) {
                throw new InvalidFormatException("deadline", "/by");
            } else if (input.length() <= input.indexOf("/by") + 4) {
                throw new EmptyDateTimeException("deadline");
            } else {
                String tempDescription = input.split(" ", 2)[1];
                String description = tempDescription.split(" /by ")[0];
                String by = tempDescription.split(" /by ")[1];

                Deadline deadline = new Deadline(description, by);
                taskList.addTask(deadline);
                String str = ui.printAddTask(taskList, deadline);
                storage.writeTasks(taskList);
                return str;
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("deadline");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
