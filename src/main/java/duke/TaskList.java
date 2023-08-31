package duke;

import java.util.ArrayList;

/**
 * Encapsulates a list of tasks to manage the current tasks on hand.
 */
public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int count = 0;

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getCount() {
        return count;
    }

    /**
     * Adds a given task to the list.
     * @param t task to be added
     * @param shouldPrint whether text should be printed, disabled when it is loaded from a file
     */
    public void addTask(Task t, boolean shouldPrint) {
        tasks.add(t);
        count++;

        if (shouldPrint) {
            System.out.println("Got it. I've added this task:");
            System.out.println(t);
            System.out.println("Now you have " + count + " task(s) in the list.");
        }
    }

    /**
     * Deletes a task of given index from the list.
     * @param index index of task to be deleted
     */
    public void deleteTask(int index) {
        Task t = getTask(index);
        tasks.remove(t);
        count--;

        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + count + " task(s) in the list.");

    }

    /**
     * Marks task of given index as done or undone.
     * @param index index of task to be marked as done or undone
     * @param isDone indicates if task is done
     */
    public void markTask(int index, boolean isDone) {
        Task t = getTask(index);
        if (isDone) {
            t.markAsDone();
        } else {
            t.markAsUndone();
        }
    }

    /**
     * Outputs all tasks in the list
     */
    public void printTasks() {
        System.out.println("List of items:");
        for (int i = 0; i < count; i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i));
        }
    }
}
