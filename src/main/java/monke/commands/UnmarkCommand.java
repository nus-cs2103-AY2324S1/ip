package monke.commands;

import monke.*;
import monke.tasks.Task;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private String taskNumber;

    public UnmarkCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        Task task = tasks.getTask(this.taskNumber);
        task.unmark();
        storage.saveData(tasks);
        ui.print("Ooga booga! I've marked this task as undone:");
        ui.print("\t" + task);
    }
}
