package chatbuddy.command;

import chatbuddy.ChatBuddyException;
import chatbuddy.storage.Storage;
import chatbuddy.TaskList;
import chatbuddy.ui.Ui;
import chatbuddy.task.Deadline;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private String taskDescription;
    private LocalDate by;

    public DeadlineCommand(String taskDescription, LocalDate by) {
        this.taskDescription = taskDescription;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Deadline deadline = new Deadline(taskDescription, by);
        tasks.addTask(deadline);
        ui.showTaskAddition(deadline, tasks.getSize());
    }
}
