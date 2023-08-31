package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

import java.io.IOException;

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
    public AddEventCommand(String taskName, String startDate, String endDate) {
        this.taskName = taskName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Event event = new Event(taskName, startDate, endDate, false);
            taskList.addTask(event);
            storage.appendToFile(event.displayableForm());
            ui.successfulAddTaskMsg(event.displayableForm(), taskList.getIndex());
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
