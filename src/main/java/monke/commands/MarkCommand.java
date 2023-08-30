package monke.commands;

import monke.*;
import monke.tasks.Task;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private String taskNumber;

    public MarkCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Task task = tasks.getTask(this.taskNumber);
        task.mark();
        storage.saveData(tasks);
        ui.print("Ooga booga! I've marked this task as done:");
        ui.print("\t" + task);
    }
}
