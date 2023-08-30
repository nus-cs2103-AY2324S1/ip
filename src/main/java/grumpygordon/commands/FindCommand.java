package grumpygordon.commands;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

public class FindCommand extends Command {
    private final String pattern;

    public FindCommand(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showCommandMessage("     There are no matching tasks in your list!\n");
        }
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).toString().contains(pattern)) {
                if (count == 0) {
                    System.out.print(Ui.SEPARATOR);
                    System.out.println("     Here are the matching tasks in your list:");
                }
                System.out.println("     " + (i + 1) + ". " + tasks.getTask(i).toString());
                count++;
            }
        }
        if (count > 0) {
            System.out.print(Ui.SEPARATOR);
        }
        if (count == 0) {
            ui.showCommandMessage("     There are no matching tasks in your list!\n");
        }
    }
}
