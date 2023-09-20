package duke.list;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Task list class for storing the current tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;


    public TaskList() {
        tasks = new ArrayList<>();

    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    public int getLength() {
        return tasks.size();
    }

    public String saveFormat() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + "\n" + tasks.get(i).saveFormat();
        }
        return output;
    }

    public void clearTasks() {
        tasks.clear();
    }
    public void addTaskList(List<Task> list) {
        tasks.addAll(list);
    }
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            output = output + String.format("%d", i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return output;
    }
}
