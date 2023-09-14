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
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String result = "";
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(command)) {
                result += (count + "." + taskList.get(i) + "\n");
                count++;
            }
        }

        return "Here are the matching tasks in your list:" +
                "\n" +
                result;
    }
}
