package duke.commands;

import java.io.IOException;
import java.util.Date;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;

/**
 * Represents a command for adding a deadline task to TaskList,
 * writing it to a file, and displaying a task added message.
 */
public class AddDeadlineCommand extends Command {
    /** Description of the deadline task */
    private final String description;
    /** Date of deadline */
    private final Date by;

    /**
     * Constructor to initialize AddDeadlineCommand.
     *
     * @param description Description of the deadline task.
     * @param by          Date of the deadline task.
     */
    public AddDeadlineCommand(String description, Date by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws IOException {
        Deadline newDeadline = new Deadline(description, by);
        taskList.addTask(newDeadline);
        Storage.save(newDeadline);
        assert taskList.countTasks() >= 0 : "Invalid task list size";
        return message.showTaskAdded(newDeadline, taskList.countTasks());
    }
}
