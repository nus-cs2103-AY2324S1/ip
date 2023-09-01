package Command;

import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;
import Tasks.Event;

/**
 * A class that is part of the command, for this in particular it will result in a event being stored.
 */
public class EventCommand extends Command{
    private String task;
    private String startDate;
    private String endDate;

    /**
     * Constructor method that will initialise all content of the user input.
     *
     * @param task the task the user wants to do
     * @param startDate the starting date
     * @param endDate the ending date
     */
    public EventCommand(String task, String startDate, String endDate) {
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * This method will form a task depending on the user inputs.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException If user inputs is invalid.
     */
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Event task = new Event(this.task, this.startDate, this.endDate);
        tasklist.add(task);
        fileStorage.write(tasklist);
        ui.showTaskAdded(task, tasklist);
    }
}
