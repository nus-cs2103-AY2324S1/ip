package Commands;

import Others.StorageManager;
import Others.Ui;
import Tasks.Deadline;
import Tasks.TaskManager;
import Tasks.Todo;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    String[] deadlineArr;
    DeadlineCommand (String input) {
        super(false);
        deadlineArr = input.replaceFirst("deadline ", "").split(" /by ");
    }

    private String convertToISO8601(String dt) {
        // dt are in the format 2022-12-01 18:00
        String[] dtArr = dt.split(" ");
        return dtArr[0] + "T" + dtArr[1];
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        String deadlineDescription = this.deadlineArr[0];
        String by = convertToISO8601(this.deadlineArr[1]); // need to add the T in
        Deadline deadline = new Deadline(deadlineDescription, LocalDateTime.parse(by));

        // add to the storage in Task & save into HDD
        taskManager.addTask(deadline);
        storageManager.save(taskManager.getTasks());

        // add to the reply
        ui.displayReply(deadline.replyString(taskManager.getTasksSize()));
    }
}
