package command;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of DeadlineCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public DeadlineCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }
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

                String[] dateTimeArr = by.split(" ");
                LocalDate byDate = LocalDate.parse(dateTimeArr[0]);
                LocalTime byTime = LocalTime.parse(dateTimeArr[1]);
                Deadline deadline = new Deadline(description, byDate, byTime);
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
