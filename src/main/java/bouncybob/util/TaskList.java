package bouncybob.util;

import bouncybob.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a list of tasks that contain the given substring in their names.
     *
     * @param subString The substring to search for in task names.
     * @return An ArrayList containing tasks that have names containing the substring.
     */
    public TaskList getSubTaskList(String subString) {
        TaskList subTaskList = new TaskList();
        for (Task task : tasks) {
            if (task.getName().contains(subString)) {
                subTaskList.addTask(task);
            }
        }
        return subTaskList;
    }
    public boolean isEmpty() {
        return tasks.size() == 0;
    }
}
