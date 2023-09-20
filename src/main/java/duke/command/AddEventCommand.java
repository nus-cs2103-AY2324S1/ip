package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.ui.Ui;
import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Represents a command to add an event task in the Duke application.
 * Extends the base Command class.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private String taskDescription;
    private String from;
    private String to;

    /**
     * Constructs an AddEventCommand with the given task description, start time, and end time.
     *
     * @param taskDescription The description of the event task.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public AddEventCommand(String taskDescription, String from, String to) {
        this.taskDescription = taskDescription;
        assert from.split(" ").length == 3 : "by String should contain should have 3 fields";
        assert from.split(" ")[0].length() == 3 : "Month is represented as 3 letters";
        assert from.split(" ")[1].length() <= 2 : "Date format should not have more than 2 characters";
        assert from.split(" ")[2].length() == 4 : "Year format should have 4 characters";
        assert to.split(" ").length == 3 : "by String should contain should have 3 fields";
        assert to.split(" ")[0].length() == 3 : "Month is represented as 3 letters";
        assert to.split(" ")[1].length() <= 2 : "Date format should not have more than 2 characters";
        assert to.split(" ")[2].length() == 4 : "Year format should have 4 characters";
        this.from = from;
        this.to = to;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException  {
        Event task = new Event(this.taskDescription, false, this.from, this.to);
        taskList.add(task);
        storage.write(taskList);
        return ui.showTaskAdded(task, taskList);
    }

}