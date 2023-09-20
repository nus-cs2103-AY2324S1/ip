package duke.commands;

import java.io.IOException;
import java.util.Date;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.storage.Storage;

/**
 * The AddDeadlineCommand adds a deadline into TaskList, writes into .txt file
 * and display a task added message when it is executed.
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
     * @param by Date of deadline.
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
