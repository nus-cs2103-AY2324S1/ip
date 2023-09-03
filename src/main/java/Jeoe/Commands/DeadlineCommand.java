package Jeoe.Commands;

import java.time.LocalDateTime;

import Jeoe.Others.StorageManager;
import Jeoe.Others.Ui;
import Jeoe.Tasks.Deadline;
import Jeoe.Tasks.TaskManager;

/**
 * This class encapsulates the class DeadlineCommand.
 * It is meant to execute the creation of a Deadline object.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class DeadlineCommand extends Command {

    /** Contains the components of the deadline command */
    private String[] deadlineArr;

    /**
     * Constructor for a DeadlineCommand object.
     * @param input The string input by the user to parse into a command.
     */
    DeadlineCommand(String input) {
        super(false);
        deadlineArr = input.replaceFirst("deadline ", "").split(" /by ");
    }

    /**
     * Converts date string inputted by user into Iso8601 format date string.
     * @param dt The string input by the user to parse into a command.
     * @return Iso8601 format date string.
     */
    private String convertToIso8601(String dt) {
        // dt are in the format 2022-12-01 18:00
        String[] dtArr = dt.split(" ");
        return dtArr[0] + "T" + dtArr[1];
    }

    /**
     * Executes the deadline command.
     * Creates a Deadline object, adds it to the task list, saves it in local storage then displays it.
     *
     * @param taskManager Task manager handling tasks operations.
     * @param ui Ui handling output to users.
     * @param storageManager Storage manager handling storing & deletion of tasks.
     */
    @Override
    public void execute(TaskManager taskManager, Ui ui, StorageManager storageManager) {
        String deadlineDescription = this.deadlineArr[0];
        String by = convertToIso8601(this.deadlineArr[1]); // need to add the T in
        Deadline deadline = new Deadline(deadlineDescription, LocalDateTime.parse(by));

        // add to the storage in Task & save into HDD
        taskManager.addTask(deadline);
        storageManager.save(taskManager.getTasks());

        // add to the reply
        ui.displayReply(deadline.replyString(taskManager.getTasksSize()));
    }
}
