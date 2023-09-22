package duke.util;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.tasks.Task;


/**
 * Class to handle the tasklist operations
 */
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Retrieves a formatted string showing the number of tasks left in the tasks.
     *
     * @return A string indicating the number of tasks remaining.
     */
    public String getTaskLeft() {
        String taskOrTasks = (tasks.size() == 1 ? " task" : " tasks");
        return "SUI, Now you have " + tasks.size() + taskOrTasks + " in the tasks.";
    }

    /**
     * Retrieves a formatted string containing all tasks in the tasks.
     *
     * @return A string tasksing all tasks in the task tasks.
     * @throws DukeException If there are no tasks in the tasks.
     */
    public String getAllToDo() throws DukeException {
        StringBuilder res = new StringBuilder();

        if (tasks.size() == 0) {
            throw new DukeException("SUI, Oh no! No tasks for now! Add more tasks :)\n");
        }

        res.append("SUI, Here are the " + tasks.size() + " tasks in your tasklist:\n");

        for (int i = 0; i < tasks.size(); i++) {
            res.append(i + 1).append(".")
                    .append(tasks.get(i).toString());
            //Append new line for all lines except last line
            if (i != tasks.size() - 1) {
                res.append("\n");
            }
        }
        return res.toString();
    }
    /**
     * Adds a task to the task list
     * @param task task to add
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    /**
     * Removes a task to the task list
     * @param index index to remove
     */
    public void removeTask(int index) {
        this.tasks.remove(index);
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    public int getSize() {
        return this.tasks.size();
    }
    public Task getTaskAtIndex(int index) {
        return this.tasks.get(index);
    }
}
