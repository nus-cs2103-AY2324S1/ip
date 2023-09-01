package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;


    /**
     * Constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Alternative constructor.
     * @param tasks Array list that already store tasks inside.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of the tasks inside array list.
     * @return The number of the tasks inside array list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the index i.
     * @param i Index of the task list that going to be access.
     * @return The task at the index i.
     * @throws DukeException If the index i out of the bound.
     */
    public Task getTask(int i) throws DukeException {
        if (i < 0 || i >= size()) {
            throw new DukeException("Index out the bounds, try another index");
        }
        return tasks.get(i);
    }

    /**
     * Adds task into the task list.
     * @param task Task that going to be added into task list.
     */
    public void addTask(Task task) {

        tasks.add(task);
    }

    /**
     * Marks the task as done.
     * @param i Index of the task that going to be mark as done.
     * @throws DukeException If index i out of the bounds.
     */
    public void markTask(int i) throws DukeException {
        if (i < 0 || i >= size()) {
            throw new DukeException("Index out the bounds, try another index");
        }
        tasks.get(i).mark();
    }

    /**
     * Unmarks the task as not done.
     * @param i Index of the task that going to be unmark as not done.
     * @throws DukeException If index in out of the bounds.
     */
    public void unmarkTask(int i) throws DukeException {
        if (i < 0 || i >= size()) {
            throw new DukeException("Index out the bounds, try another index");
        }
        tasks.get(i).unmark();
    }

    /**
     * Deletes the task at index i.
     * @param i Index of the task that going to be deleted.
     * @throws DukeException If index out the bounds.
     */
    public void deleteTask(int i) throws DukeException {
        if (i < 0 || i >= size()) {
            throw new DukeException("Index out the bounds, try another index");
        }
        tasks.remove(i);
    }

    public TaskList findTasks(String keyword) {
        TaskList newTaskList = new TaskList();
        for (Task task: tasks) {
            if (task.isContains(keyword)) {
                newTaskList.addTask(task);
            }
        }
        return newTaskList;
    }
}
