package duke.commands;

import duke.utils.*;
import duke.tasks.*;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes the task indicated by taskNumber.
     *
     * @param storage
     * @param tasks
     */
    @Override
    public void execute(Storage storage, ArrayList<Task> tasks) {
        try {
            Task task = tasks.get(taskNumber - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            tasks.remove(this.taskNumber-1);
            Ui.printLine();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("The selected task does not exist");
            Ui.printLine();
        }
    }
}
