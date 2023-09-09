package commands;

import components.Storage;
import components.Ui;
import tasks.TaskList;

public class FindCommand extends Command {
    private String command;

    public FindCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(Ui.LINE);
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(command)) {
                System.out.println(count + "." + taskList.get(i));
                count++;
            }
        }
        System.out.println(Ui.LINE);
    }
}
