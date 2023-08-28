package duke.main;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public String find(String keyword) {
        String matchingTasks = "";
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.showContent().contains(keyword)) {
                matchingTasks += String.format("  %d. %s\n", index, currentTask.toString().trim());
                index += 1;
            }
        }
        return matchingTasks;
    }
}
