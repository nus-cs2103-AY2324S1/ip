package Jelly.main;

import Jelly.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void delete(int index) {
        taskList.remove(index);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void markAsUndone(int index) {
        taskList.get(index).markAsUndone();
    }

    /**
     * Filters the list of tasks to correspond to tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return The arraylist of tasks that contains the keyword specified.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                matchingTasks.add(taskList.get(i));
            }
        }
        return matchingTasks;
    }
}
