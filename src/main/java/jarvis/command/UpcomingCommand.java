package jarvis.command;

import java.time.LocalDateTime;

import jarvis.exception.JarvisException;
import jarvis.storage.Storage;
import jarvis.task.Deadline;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to display the nearest upcoming deadline that is not done yet.
 */
public class UpcomingCommand extends Command {

    /**
     * Executes the UpcomingCommand.
     * Displays the nearest upcoming deadline that is not done yet.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui object, for displaying the unmarked task to the user.
     * @param storage The Storage object, for saving the updated task list.
     * @throws JarvisException If there is any error during the command execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        LocalDateTime now = LocalDateTime.now();
        Deadline upcomingDeadline = tasks.getUpcomingDeadline(now);
        return ui.displayUpcomingDeadline(upcomingDeadline);
    }
}
