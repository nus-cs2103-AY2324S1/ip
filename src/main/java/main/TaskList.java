package main;

import exception.DialogixException;

import task.Task;

import java.util.ArrayList;
import java.util.Stack;

public class TaskList {
    private ArrayList<Task> tasks;
    private final Stack<ArrayList<Task>> taskListStack;

    TaskList() {
        this.tasks = new ArrayList<>();
        taskListStack = new Stack<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        taskListStack = new Stack<>();
    }

    /**
     * Adds a deep copy of itself to task list stack.
     */
    public void addToStack() {
        ArrayList<Task> newList = new ArrayList<>(tasks);
        taskListStack.push(newList);
    }

    /**
     * Restores task list by performing the undo operation.
     *
     * @param noOfSteps the num of steps
     */
    public void undo(int noOfSteps) {
        for (int i = 0; i < noOfSteps - 1; i++) {
            taskListStack.pop();
        }
        tasks = taskListStack.pop();
    }

    /**
     * Gets max undo.
     *
     * @return the max undo
     */
    public int getMaxUndo() {
        return taskListStack.size();
    }

    /**
     * Gets all tasks present.
     *
     * @return The list of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns the task given the zero based index of the task.
     *
     * @param zeroBasedIndex The given index to be returned.
     */
    public Task get(int zeroBasedIndex) {
        assert zeroBasedIndex >= 0 && zeroBasedIndex < tasks.size();
        return tasks.get(zeroBasedIndex);
    }

    /**
     * Marks a task as done given the zero based index of the task.
     *
     * @param zeroBasedIndex The given index to be deleted.
     */
    public void markTaskAsDone(int zeroBasedIndex) {
        assert zeroBasedIndex >= 0 && zeroBasedIndex < tasks.size();
        tasks.get(zeroBasedIndex).markAsDone();
    }

    /**
     * Adds a task to the back of the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert task != null;
        tasks.add(task);
    }

    /**
     * Deletes a task from the list given the zero based index of the task.
     *
     * @param zeroBasedIndex The given index to be deleted.
     */
    public void delete(int zeroBasedIndex) throws DialogixException {
        if (zeroBasedIndex < 0 || zeroBasedIndex >= tasks.size()) {
            throw new DialogixException("OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        tasks.remove(zeroBasedIndex);
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Finds tasks in the list containing the given String in their description.
     *
     * @param toFind The criteria to filter tasks.
     * @return An ArrayList containing all tasks containing thg given String in their description.
     */
    public ArrayList<Task> find(String toFind) {
        assert !toFind.isBlank();

        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().contains(toFind)) {
                filteredTasks.add(t);
            }
        }
        return filteredTasks;
    }
}
