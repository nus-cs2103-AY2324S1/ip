package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class EventCommand extends TaskCommand {

    protected LocalDate fromDate;
    protected LocalTime fromTime;
    protected LocalDate toDate;
    protected LocalTime toTime;

    public EventCommand(String description, boolean isDone, LocalDate fromDate, LocalTime fromTime, LocalDate toDate,
            LocalTime toTime) {
        super(description, isDone);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    @Override
    public void load(TaskList tasklist) {
        tasklist.add(description, isDone, fromDate, fromTime, toDate, toTime);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        String eventString = tasklist.add(description, isDone, fromDate, fromTime, toDate, toTime);
        ui.print(String.format("I've added this event:\n%s\nNow you have %d tasks in the list.", eventString,
                tasklist.getSize()));
    }
}
