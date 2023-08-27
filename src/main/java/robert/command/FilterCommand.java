package robert.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Deadline;
import robert.task.Event;
import robert.task.Task;
import robert.task.TaskList;
import robert.ui.Ui;

/**
 * A Filter extension of the <tt>Command</tt> class. Used to filter out
 * tasks that have deadline or an event happening on a particular date.
 *
 * @author Lee Zhan Peng
 */
public class FilterCommand extends Command {

    /** The date that is used for querying */
    private final LocalDate date;

    /**
     * Constructs FilterCommand using a date.
     *
     * @param date the date to be used for querying of tasks.
     */
    public FilterCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Executes the filtering of tasks.
     *
     * @param tasks the list of tasks to be added onto.
     * @param ui the ui that is responsible for the output of the CLI.
     * @param storage the storage that loads stored tasks from hard disk.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {

        int deadlineIndex = 0;
        StringBuilder deadlinesOnDate = new StringBuilder();
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).isDueOn(this.date)) {
                deadlineIndex++;
                deadlinesOnDate.append(deadlineIndex).append(". ").append(task).append("\n");
            }
        }

        int eventIndex = 0;
        StringBuilder eventsHappeningOnDate = new StringBuilder();
        for (Task task : tasks) {
            if (task instanceof Event && ((Event) task).isHappeningOn(this.date)) {
                eventIndex++;
                eventsHappeningOnDate.append(eventIndex).append(". ").append(task).append("\n");
            }
        }

        if (deadlineIndex == 0 && eventIndex == 0) {
            ui.showMessage("There are no tasks that are due and happening on "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ".");
            return;
        }

        String output = "";

        if (deadlineIndex > 0) {
            output += (deadlineIndex == 1 ? "This is the task that is" : "Here are the tasks that are")
                    + " due on " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n";
        }
        output += deadlinesOnDate.toString();

        if (eventIndex > 0) {
            output += (eventIndex == 1 ? "This is the task that is" : "Here are the tasks that are")
                    + " happening on " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n";
        }
        output += eventsHappeningOnDate.toString();
        ui.showMessage(output);
    }
}
