package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * @author juzzztinsoong
 */
public class DeadlineCommand extends TaskCommand {

    private LocalDate byDate;
    private LocalTime byTime;

    /**
     * Constructor method for DeadlineCommand.
     * @param description the description of the deadline. Cannot be empty.
     * @param isDone true if the deadline is done, false otherwise.
     * @param byDate the date to use for the deadline. Will not be displayed if null.
     * @param byTime the time to use for the deadline. Will not be displayed if null.
     */
    public DeadlineCommand(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public void load(TaskList tasklist) {
        tasklist.add(description, isDone, byDate, byTime);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        String deadlineString = tasklist.add(description, isDone, byDate, byTime);
        ui.print(
                String.format("I've added this deadline:\n%s\nNow you have %d tasks in the list.", deadlineString,
                        tasklist.getSize()));
    }
}
