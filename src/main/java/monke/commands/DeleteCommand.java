package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private String taskNumber;

    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        tasks.delete(this.taskNumber);
        storage.saveData(tasks);

        ui.print("Noted. I've removed this task:");
        ui.print("\t" + tasks.getTask(this.taskNumber));
        ui.print("Now you have " + tasks.size() + " tasks in the list.");
    }
}
