package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static List<Task> allTasks;

    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public TaskList(List<Task> initialTasks) {
        allTasks = new ArrayList<>(initialTasks);
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < allTasks.size()) {
            allTasks.remove(taskIndex);
        }
    }

    public List<Task> getTasks() {
        return allTasks;
    }

    public static boolean isTaskInAllTasks(String taskType, String taskDescription) {
        for (Task task : allTasks) {
            if (task.getTask().equals(taskDescription)
                    && task.getTaskType().equals(taskType)) {
                return true;
            }
        }
        return false;
    }
}
