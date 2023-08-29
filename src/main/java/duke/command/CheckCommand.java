package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to check and display tasks on a specific date.
 */
public class CheckCommand extends Command {
    private String dateToCheck;

    /**
     * Constructs a CheckCommand with the provided date.
     *
     * @param dateToCheck The date to check for tasks.
     */
    public CheckCommand(String dateToCheck) {
        this.dateToCheck = dateToCheck;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (dateToCheck.trim().isEmpty()) {
                throw new EmptyDescriptionException("date");
            }

            LocalDate date = LocalDate.parse(dateToCheck.trim(), DateTimeFormatter.ofPattern("d/M/yyyy"));
            ui.showTasksOnDate(date, taskList);

        } catch (EmptyDescriptionException e) {
            ui.showDukeException(e);
        } catch (DateTimeParseException e) {
            ui.showInvalidDateFormat();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
