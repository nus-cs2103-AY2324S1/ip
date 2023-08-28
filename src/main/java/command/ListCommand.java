package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            Task currTask = taskList.getTask(i);
            String listCounter = (i + 1) + ".";
            System.out.println(listCounter + currTask.toString());
        }
        System.out.println("");
    }

}
