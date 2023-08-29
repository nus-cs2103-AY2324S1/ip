package duke;

import java.util.List;

public class TaskList {
    private final List<Task> userList;

    public TaskList(List<Task> taskList) {
        this.userList = taskList;
    }

    public void addTask(Task task) {
        userList.add(task);
    }

    public void markTask(Task task) {
        task.markAsDone();
        Ui.showMarkedTask(task);
    }

    public void unmarkTask(Task task) {
        task.markAsUndone();
        Ui.showUnmarkedTask(task);
    }

    public void deleteTask(Task task) {
        userList.remove(task);
        Ui.showDeletedTask(task, this);
    }

    public int getSize() {
        return userList.size();
    }

    public Task getTask(int index) {
        return userList.get(index);
    }
}