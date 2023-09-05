package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            Task currTask = taskList.getTask(i);
            String listCounter = (i + 1) + ".";
            str.append(listCounter + currTask.toString() + "\n");
        }
        str.append("\n");
        return str.toString();
    }

}
