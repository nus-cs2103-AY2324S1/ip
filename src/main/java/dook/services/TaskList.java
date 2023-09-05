package dook.services;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

import dook.DookException;
import dook.task.Task;

/**
 * Wrapper class for the task list containing methods to manipulate the tasks within.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the end of list.
     * @param t Task to add to the list.
     * @return Confirmation message to be displayed to the user.
     */
    public String addTask(Task t) {
        taskList.add(t);
        return (String.format("added: %s.\nNow you have %d %s in the list.",
                t,
                taskList.size(),
                taskList.size() == 1 ? "task" : "tasks"));
    }
    /**
     * Deletes a task at a specified index in the list.
     * @param index Index of task to be deleted from the list.
     * @return Confirmation message to be displayed to the user.
     */
    public String deleteTask(int index) throws DookException {
        if (index <= 0 || index > taskList.size()) {
            throw new DookException("That task does not exist on the list.");
        }

        Task curr = taskList.get(index - 1);
        taskList.remove(index - 1);
        String message = String.format("Ok, I have removed this task:\n   %s", curr);
        message += String.format("\nYou have %d %s in the list.",
                taskList.size(),
                taskList.size() == 1 ? "task" : "tasks");
        return message;
    }

    /**
     * Marks a task at a specified index in the list.
     * @param index Index of task to be marked in the list.
     * @param value Value to mark the task with.
     * @return Confirmation message to be displayed to the user.
     * @throws DookException Exception thrown by Dook.
     */
    public String markTask(int index, boolean value) throws DookException {
        if (index <= 0 || index > taskList.size()) {
            throw new DookException("That task does not exist on the list.");
        }
        Task curr = taskList.get(index - 1);
        if (value) {
            curr.markAsDone();
        } else {
            curr.unmarkAsDone();
        }
        return String.format("I have marked this task as %s:\n   %s",
                value ? "done" : "not done yet", curr);
    }

    /**
     * Filters the task list using provided filter function and gets a
     * string containing all that pass.
     *
     * @param f Filter function to apply on tasks.
     * @return String containing all tasks that meet the requirement.
     */
    public String filterTasks(Function<Task, Boolean> f) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (f.apply(task)) {
                result.append(String.format("%d. %s\n", i + 1, task));
            }
        }
        return result.toString();
    }

    /**
     * Accumulates task list to a result of type T.
     * @param acc Given accumulator function.
     * @param identity Identity value of result.
     * @param <T> Type of result.
     * @return The accumulated result.
     */
    public <T> T accumulateTasks(BiFunction<? super Task, ? super T, ? extends T> acc, T identity) {
        T result = identity;
        for (Task task : taskList) {
            result = acc.apply(task, result);
        }
        return result;
    }

    @Override public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, taskList.get(i)));
        }
        return (result + String.format("You have %d %s in the list.",
                taskList.size(),
                taskList.size() == 1 ? "task" : "tasks"));
    }
}
