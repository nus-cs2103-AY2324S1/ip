package commands;

import java.time.LocalDateTime;
import java.util.List;

import data.Actions;
import duke.DukeException;
import tasks.Task;
import ui.UI;

/**
 * Represents the command to display tasks scheduled on a specific date.
 */
public class ScheduleCommand extends Command {
    private final String dateInput;

    /**
     * Initializes a ScheduleCommand with the date for which the scheduled tasks should be shown.
     *
     * @param dateInput The date string provided by the user.
     */
    public ScheduleCommand(String dateInput) {
        this.dateInput = dateInput;
    }

    /**
     * Executes the ScheduleCommand by retrieving and displaying tasks scheduled on the specified date.
     * If no tasks are found for the date, a notification is shown to the user. Otherwise, a list
     * of tasks for the date is displayed.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException If there are issues with parsing or handling the date.
     */
    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        Task dateUtility = new Task("");
        LocalDateTime dateToShow = dateUtility.stringToDate(dateInput);
        List<Task> tasksOnDate = actionList.tasksOnDate(dateToShow);
        if (tasksOnDate.isEmpty()) {
            ui.lineSandwich(" No tasks found on " + dateUtility.dateToString(dateToShow) + ".");
        } else {
            StringBuilder tasksMessage = new StringBuilder();
            for (int i = 0; i < tasksOnDate.size(); i++) {
                tasksMessage.append(" ").append(i + 1).append(". ").append(tasksOnDate.get(i)).append("\n");
            }
            ui.lineSandwich(" Tasks on " + dateUtility.dateToString(dateToShow) + ":\n" + tasksMessage);
        }
    }
}
