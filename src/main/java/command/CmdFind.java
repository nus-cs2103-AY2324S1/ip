package command;

import task.Task;
import task.TaskList;
import ui.Ui;

public class CmdFind extends Command {
    private String keyword;

    public CmdFind(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task[] filteredList = taskList.search(keyword);
        ui.print("Here are the matching tasks in your list:");
        for (int i = 1; i < filteredList.length + 1; i++) {
            Task task = filteredList[i - 1];
            ui.print(String.format("%d. %s", i, task.toString()));
        }
    }

}
