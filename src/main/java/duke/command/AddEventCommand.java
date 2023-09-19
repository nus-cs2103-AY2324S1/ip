package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Event;

/**
 * The AddEventCommand.
 */
public class AddEventCommand extends Command {
    private String taskName;
    private String startDate;
    private String endDate;

    /**
     * Constructor for the duke.command.Command class.
     *
     * @param taskName
     * @param startDate
     * @param endDate
     */
    public AddEventCommand(String taskName, String startDate, String endDate) throws DukeException {
        if (taskName.equals("")) {
            throw new DukeException("☹ OOPS!!! Incorrect description of a deadline.");
        }
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Executes the command by creating an event, adding it to the taskList,
     * adding it to the storage and displaying a message to the ui.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Event event = new Event(taskName, startDate, endDate, false);
            taskList.addTask(event);
            storage.rewriteToFile(taskList.getList());
            return ui.successfulAddTaskMsg(event.userDisplayString(), taskList.getIndex());
        } catch (Exception e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
