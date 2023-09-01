package Command;

import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;
import Tasks.Event;

public class EventCommand extends Command{
    private String task;
    private String startDate;
    private String endDate;
    public EventCommand(String task, String startDate, String endDate) {
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Event task = new Event(this.task, this.startDate, this.endDate);
        tasklist.add(task);
        fileStorage.write(tasklist);
        ui.showTaskAdded(task, tasklist);
    }
}
