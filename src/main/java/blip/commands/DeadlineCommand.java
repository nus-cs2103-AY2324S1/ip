package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime deadline;

    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        Deadline deadlineTask = new Deadline(description, deadline, false);
        taskList.addTask(deadlineTask);
        storage.saveToFile(taskList);
        ui.addsTasksMsg(deadlineTask, taskList.size());
    }
}
