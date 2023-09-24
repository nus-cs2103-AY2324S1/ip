package command;

import exception.DukeException;
import task.Task;
import task.Event;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to view the schedule for a specific date.
 */
public class ViewScheduleCommand extends Command {

    private final LocalDate date;

    /**
     * Constructs a ViewScheduleCommand for the specified date.
     *
     * @param dateStr The date for which the schedule should be viewed in the form of a string.
     * @throws DukeException if the dateStr is empty or cannot be parsed.
     */
    public ViewScheduleCommand(String dateStr) throws DukeException {
        super(null);
        if (dateStr == null || dateStr.trim().isEmpty()) {
            throw new DukeException("Date string cannot be empty.");
        }
        try {
            this.date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please enter the date in YYYY-MM-DD format.");
        }
    }

    /**
     * Executes the ViewScheduleCommand, displaying the tasks scheduled for the specified date.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file (not used in this command).
     * @return A string representing the schedule for the specified date.
     * @throws DukeException if there are no tasks on the specified date.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("No tasks found for the specified date.");
        }
        List<Task> schedule = tasks.getList().stream()
                .filter(task -> {
                    if (task.getDate() != null && task.getDate().toLocalDate().equals(date)) {
                        return true;
                    } else if (task instanceof Event) {
                        Event event = (Event) task;
                        LocalDate toDate = event.getToDate().toLocalDate();
                        LocalDate fromDate = event.getDate().toLocalDate();
                        return !date.isBefore(fromDate) && !date.isAfter(toDate);
                    }
                    return false;
                })
                .collect(Collectors.toList());
        return ui.showSchedule(schedule);
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return False, indicating that this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}