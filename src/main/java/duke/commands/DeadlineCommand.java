package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.tasks.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

public class DeadlineCommand extends TaskCommand {

    private LocalDate byDate;
    private LocalTime byTime;

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
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        String deadlineString = tasklist.add(description, isDone, byDate, byTime);
        ui.print(
                String.format("I've added this deadline:\n%s\nNow you have %d tasks in the list.", deadlineString,
                        tasklist.getSize()));
    }
}
