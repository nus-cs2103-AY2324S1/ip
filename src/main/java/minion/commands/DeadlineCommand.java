package minion.commands;

import minion.data.task.Deadline;
import minion.storage.Storage;
import minion.data.TaskList;
import minion.ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final Deadline deadline;
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(deadline);
        ui.print(
                "Got it. I've added this task:",
                "\t" + deadline.toString(),
                "Now you have " + tasks.size() +  " tasks in the list."
        );
        storage.writeToFile(tasks);
    }
}
