package duke;

import java.util.ArrayList;
import java.util.List;

public class Ui {

    public String listFoundTasks(List<Task> filteredTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < filteredTasks.size(); i++) {
            sb.append(String.format("%d.%s\n", i + 1, filteredTasks.get(i).toString()));
        }
        return sb.toString();
    }

    public String listMessage(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(String.format("%d.%s%n", i + 1, task));
        }
        return sb.toString();
    }

    public String markTaskAsDoneMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(String.format("%s%n", task));
        return sb.toString();
    }

    public String unmarkTaskMessage(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("OK, I've marked this task as not done yet:\n");
        sb.append(String.format("%s%n", task));
        return sb.toString();
    }

    public String deleteTaskMessage(Task task, int numOfTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append(String.format("%s\n", task));
        sb.append(String.format("Now you have %d tasks in the list.%n", numOfTasks));
        return sb.toString();
    }

    public String addTaskMessage(Task task, int numOfTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Got it. I've added this task:\n");
        sb.append(String.format("%s\n", task));
        sb.append(String.format("Now you have %d tasks in the list.%n", numOfTasks));
        return sb.toString();
    }
}
