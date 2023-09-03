package duke.commands;

import duke.exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to find a task from a given string prefix.
 */
public class FindCommand extends Command {
    private String prefix;

    public FindCommand(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        int count = 0;
        try {
            System.out.println(Ui.divider + "\n" + "Here are the matching tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                if (taskList.getTask(i).getTaskName().matches("(.*)" + this.prefix + "(.*)")) {
                    System.out.println((count + 1) + "." + taskList.getTask(i));
                    count++;
                }
            }
            System.out.println(Ui.divider + "\n");
        } catch (InvalidTaskIndexException ex) {
            System.out.println("Something went wrong.");
        }
    }
}
