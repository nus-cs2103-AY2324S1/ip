package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.mark();
            return "Nice! I've marked this task as done:\n" + task.toString();
        }
        return "Invalid task index.";
    }

    public String markTaskAsNotDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.unMark();
            return "OK, I've marked this task as not done yet:\n" + task.toString();
        }
        return "Invalid task index.";
    }

    public String displayTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

}
