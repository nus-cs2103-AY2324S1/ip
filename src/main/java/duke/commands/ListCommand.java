package duke.commands;

import duke.utils.*;
import duke.tasks.*;
import java.util.ArrayList;

/**
 * A class for handling list commands.
 */
public class ListCommand extends Command {

    /**
     * Lists out all tasks.
     *
     * @param storage
     * @param tasks
     */
    @Override
    public void execute(Storage storage, ArrayList<Task> tasks) {
        System.out.println("Here are your current tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.println((i + 1) + "." + currentTask);
        }
        Ui.printLine();
    }
}
