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
    public void execute(TaskList taskList, Ui ui) {
        int count = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            Task currTask = taskList.getTask(i);
            if (currTask.toString().contains(this.keyword)) {
                String listCounter = count + ".";
                System.out.println(listCounter + currTask);
                count++;
            }
        }
        System.out.println();
    }
}
