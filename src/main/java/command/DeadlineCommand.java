package command;

import duke.DiskManager;
import duke.TaskManager;
import duke.Ui;
import task.Deadline;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDate deadline;
    
    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }
    
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) {
        ui.printOutput(taskManager.addTask(new Deadline(description, deadline)));
        diskManager.saveToDisk(taskManager);
    }
}
