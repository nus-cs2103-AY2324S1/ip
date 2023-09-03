package tasks;

import exceptions.JamesBondException;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;


import exceptions.JamesBondException;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The `TaskList` class represents a collection of tasks. It provides methods for managing tasks
 * such as adding, deleting, retrieving, and finding tasks.
 */
public class TaskList {
    ArrayList<Task> toDos;

    /**
     * Constructs a `TaskList` object with an initial capacity of 100 tasks.
     */
    public TaskList() {
        toDos = new ArrayList<>(100);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        toDos.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void delete(int index) {
        toDos.remove(index);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int len() {
        return this.toDos.size();
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return toDos.get(index);
    }

    /**
     * Returns a list of all tasks in the task list.
     *
     * @return An ArrayList of all tasks in the task list.
     */
    public ArrayList<Task> getToDos() {
        return toDos;
    }

    /**
     * Finds the index of a task with the specified description in the task list.
     *
     * @param task The description of the task to be found.
     * @return The index of the task if found, or -1 if not found.
     */
    public int findTask(String task) {
        for (int i = 0; i < toDos.size(); i++) {
            if (task.equalsIgnoreCase(toDos.get(i).text)) {
                return i;
            }
        }
        return -1;
    }
}

