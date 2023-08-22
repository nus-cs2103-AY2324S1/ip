package java.doctor.store;

import java.doctor.Task.*;

public class store {
    private static store store = new store();
    Task[] tasks = new Task[100];
    int taskCount = 0;

    private store() {
    }

    public static store getInstance() {
        return store;
    }

    public boolean addTask(Task task) {
        if (taskCount == 100) {
            return false;
        }
        tasks[taskCount] = task;
        taskCount++;
        return true;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        if (index > taskCount || index < 1) {
            return null;
        }
        return tasks[index - 1];
    }

    public boolean deleteTask(int index) {
        if (index > taskCount || index < 1) {
            return false;
        }
        for (int i = index - 1; i < taskCount - 1; i++) {
            tasks[i] = tasks[i + 1];
        }
        taskCount--;
        return true;
    }

    public boolean mark(int index) {
        if (index > taskCount || index < 1) {
            return false;
        }
        tasks[index-1].mark();
        return true;
    }

    public boolean unmark(int index) {
        if (index > taskCount || index < 1) {
            return false;
        }

        tasks[index-1].unmark();
        return true;
    }

    public boolean updateDescription(int index, String description) {
        if (index > taskCount || index < 1) {
            return false;
        }
        tasks[index-1].setDescription(description);
        return true;
    }  

    public int getTaskCount() {
        return taskCount;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskCount; i++) {
            result += (i + 1) + ". " + tasks[i] + "\n";
        }
        return result;
    }
}
