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
     * @return string output after adding task
     */
    public String addTask(Task t, boolean shouldPrint) {
        tasks.add(t);
        count++;

        StringBuilder output = new StringBuilder();
        if (shouldPrint) {
            output.append("Got it. I've added this task:\n");
            output.append(t.toString() + "\n");
            output.append("Now you have " + count + " task(s) in the list.");
        }

        return output.toString();
    }

    /**
     * Deletes a task of given index from the list.
     * @param index index of task to be deleted
     * @return string output after deleting task
     */
    public String deleteTask(int index) {
        Task t = getTask(index);
        tasks.remove(t);
        count--;

        StringBuilder output = new StringBuilder();
        output.append("Noted. I've removed this task:\n");
        output.append(t.toString() + "\n");
        output.append("Now you have " + count + " task(s) in the list.");

        return output.toString();
    }

    /**
     * Marks task of given index as done or undone.
     * @param index index of task to be marked as done or undone
     * @param isDone indicates if task is done
     * @return string output on task that was marked as done or undone
     */
    public String markTask(int index, boolean isDone) {
        Task t = getTask(index);
        if (isDone) {
            return t.markAsDone();
        } else {
            return t.markAsUndone();
        }
    }

    /**
     * Finds tasks in lists that contain the given string.
     * @param toFind string to be searched for
     * @return list of tasks that contain the given string
     */
    public String findTasks(String toFind) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        int index = 1;
        for (int i = 0; i < count; i++) {
            if (tasks.get(i).getName().contains(toFind)) {
                output.append(index + "." + tasks.get(i) + "\n");
                index++;
            }
        }

        if (index == 1) {
            // None found
            output.append("No matching tasks found.");
        }

        return output.toString();
    }
    /**
     * Outputs all tasks in the list
     * @return string output of all tasks in the list
     */
    public String printTasks() {
        StringBuilder output = new StringBuilder("List of items:\n");
        for (int i = 0; i < count; i++) {
            int index = i + 1;
            output.append(index + "." + tasks.get(i) + "\n");
        }

        return output.toString();
    }
}
