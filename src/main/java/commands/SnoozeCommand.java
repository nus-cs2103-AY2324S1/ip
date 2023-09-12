package commands;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import exceptions.DukeException;
import io.Storage;
import tasks.DeadlineTask;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command that executes snoozing a task.
 */
public class SnoozeCommand extends Command {
    /**
     * Constructor for SnoozeCommand.
     * @param fullCommand The command to be executed
     */
    public SnoozeCommand(String fullCommand) {
        super(fullCommand);
    }
    /**
     * Executes the SnoozeCommand, snoozing a task.
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     * @throws DukeException If the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String[] commandArray = getFullCommand().split(" ");
            int taskNumber = Integer.parseInt(commandArray[1]);
            // snooze 1 /by 2/12/2019 1800
            String[] commandArrayBy = getFullCommand().split(" /by ");
            String dataString = commandArrayBy[1];
            System.out.println(dataString);
            LocalDateTime byDateTime;
            try {
                byDateTime = LocalDateTime.parse(dataString, Task.getDateFormat());
            } catch (DateTimeException e) {
                throw new DukeException("Date should follow the format d/M/yyyy HHmm");
            }
            try {
                DeadlineTask task = (DeadlineTask) taskList.getTask(taskNumber);
                task.setByDateTime(byDateTime);
                ui.showsnoozeTask(task);
            } catch (ClassCastException e) {
                throw new DukeException("Task is not a deadline task");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid date in the format: dd/mm/yyyy");
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a valid task number");
        } catch (DateTimeException e) {
            throw new DukeException("Please enter a valid date");
        }
    }
}
