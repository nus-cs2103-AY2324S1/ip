package pkg.store;

import pkg.task.Task;

public class store {
    private static store store = new store();
    Task[] tasks = new Task[100];
    int taskCount = 0;

    private store() {
    }

    public static store getInstance() {
        return store;
    }

    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public void deleteTask(int index) {
        for (int i = index; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        taskCount--;
    }

    public void markTask(int index) {
        tasks[index].mark();
    }

    public void unmarkTask(int index) {
        tasks[index].unmark();
    }

    public void updateDescription(int index, String description) {
        tasks[index].updateDescription(description);
    }  

    public int getTaskCount() {
        return taskCount;
    }
}
