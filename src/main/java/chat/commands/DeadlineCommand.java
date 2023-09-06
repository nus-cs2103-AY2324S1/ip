package chat.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import chat.exceptions.ChatException;
import chat.tasks.TaskList;
import chat.utils.Storage;

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
    public String execute(TaskList tasklist, Storage storage) {
        String deadlineString = tasklist.add(description, isDone, byDate, byTime);
        try {
            storage.writeToFile(tasklist);
            return String.format(
                "I've added this deadline:\n%s\nNow you have %d tasks in the list.",
                deadlineString, tasklist.getSize());
        } catch (ChatException e) {
            return e.getMessage();
        }
    }
}
