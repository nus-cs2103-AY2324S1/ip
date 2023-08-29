package duke.commands;

import duke.Exceptions.InvalidTaskIndexException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
                if (taskList.getTask(i).getTaskName().matches("(.*)"+ this.prefix + "(.*)")) {
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
