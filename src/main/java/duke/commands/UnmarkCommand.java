package duke.commands;

import duke.utils.*;
import duke.tasks.*;
import java.util.ArrayList;

/**
 * A class for handling unmark commands.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task indicated by taskNumber as not completed.
     *
     * @param storage
     * @param tasks
     */
    @Override
    public void execute(Storage storage, ArrayList<Task> tasks) {
        try {
            tasks.get(taskNumber - 1).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskNumber - 1));
            Ui.printLine();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("The selected task does not exist");
            Ui.printLine();
        }
    }
}
