package command;

import duke.DiskManager;
import duke.TaskManager;
import duke.Ui;
import task.Event;

import java.time.LocalDate;

public class EventCommand extends Command {
    private String description;
    private LocalDate start;
    private LocalDate end;

    public EventCommand(String description, LocalDate start, LocalDate end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) {
        ui.printOutput(taskManager.addTask(new Event(description, start, end)));
        diskManager.saveToDisk(taskManager);
    }
}
