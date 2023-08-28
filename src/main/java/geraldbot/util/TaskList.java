package geraldbot.util;

import geraldbot.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task remove(int idx) {
        Task removedTask = this.taskList.remove(idx);
        return removedTask;
    }

    public Task get(int idx) {
        Task selectedTask = this.taskList.get(idx);
        return selectedTask;
    }

    public int size() {
        return this.taskList.size();
    }

    /**
     * Finds tasks in the list that match the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks matching the keyword.
     */
    public List<Task> findTasksByKeyword(String keyword) {
        return taskList.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
    }
}
