package duke.command;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.ui.Ui;
import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to add a deadline task in the Duke application.
 * Extends the base Command class.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String taskDescription;
    private String by;

    /**
     * Constructs an AddDeadlineCommand with the given task description and due date.
     *
     * @param taskDescription The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public AddDeadlineCommand(String taskDescription, String by) {
        assert by.split(" ").length == 3 : "by String should contain should have 3 fields";
        assert by.split(" ")[0].length() == 3 : "Month is represented as 3 letters";
        assert by.split(" ")[1].length() <= 2 : "Date format should not have more than 2 characters";
        assert by.split(" ")[2].length() == 4 : "Year format should have 4 characters";
        this.taskDescription = taskDescription;
        this.by = by;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException  {
        Deadline task = new Deadline(this.taskDescription, false, this.by);
        taskList.add(task);
        storage.write(taskList);
        return ui.showTaskAdded(task, taskList);
    }

}
