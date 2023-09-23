package command;

import exception.DukeException;
import task.Task;
import task.Event;
import task.TaskList;
import ui.Ui;
import storage.Storage;

import java.time.LocalDate;
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
     * @param date The date for which the schedule should be viewed.
     */
    public ViewScheduleCommand(LocalDate date) {
        super(null);
        this.date = date;
    }

    /**
     * Executes the ViewScheduleCommand, displaying the tasks scheduled for the specified date.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file (not used in this command).
     * @return A string representing the schedule for the specified date.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (tasks.size() == 0) {
            throw new DukeException("Please specify a date to view the schedule for.");
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
