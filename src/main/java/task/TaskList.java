package task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.getSize() == 0;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(Task task) {
        this.tasks.remove(task);
    }

    public void markTask(Task task) {
        task.markAsDone();
    }

    public void unmarkTask(Task task) {
        task.markAsUndone();
    }

    public void printTaskList() {
        int index = 1;
        System.out.println(" Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.printf("  %d. %s\n", index, task.toString());
            index++;
        }
    }

    public ArrayList<Task> filterTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        String keywordLowercase = keyword.toLowerCase();
        for (Task task : tasks) {
            String description = task.getDescription().toLowerCase();
            if (description.contains(keywordLowercase)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }
}
