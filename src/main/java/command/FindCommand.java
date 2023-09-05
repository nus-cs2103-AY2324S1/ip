package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class FindCommand extends Command{
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        int count = 1;
        str.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            Task currTask = taskList.getTask(i);
            if (currTask.toString().contains(this.keyword)) {
                String listCounter = count + ".";
                str.append(listCounter + currTask + "\n");
                count++;
            }
        }
        return str.toString();
    }
}
