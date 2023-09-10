package prts;

import java.io.Serializable;
import java.util.ArrayList;

import prts.task.AlreadyMarkedException;
import prts.task.AlreadyUnmarkedException;
import prts.task.Task;

/**
 * Represents the list of Tasks the user has added in PRTS, and manages all modifications to this list.
 */
public class TaskList implements Serializable {

    private final ArrayList<Task> tasks;

    /**
     * Constructs a new, empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new Task to the list.
     * @param task The Task to be added to the list.
     * @return A message to be displayed to the user as acknowledgement and indication of successful
     *         execution.
     */
    public String add(Task task) {
        assert task != null : "Task cannot be null!";
        tasks.add(task);
        return task.getAddMessage() + "\n" + task;
    }

    /**
     * Deletes a Task from the list.
     * @param index The index of the Task to be deleted from the list.
     *              This is a number from 1 to the size of the list, or null if all items should be
     *              deleted.
     * @return A message to be displayed to the user as acknowledgement and indication of successful
     *         execution.
     * @throws OutOfRangeException If the index provided is 0 or less, or greater than the size of
     *         the list.
     */
    public String delete(Integer index) throws OutOfRangeException {

        if (index == null) {
            tasks.clear();
            return "Your task list has been cleared.";
        }

        int i = index - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new OutOfRangeException();
        }

        Task task = tasks.remove(i);
        StringBuilder builder = new StringBuilder();

        builder.append("As you wish. This task has been removed:\n");
        builder.append(task.toString());
        builder.append("\nYou now have ");
        builder.append(tasks.size());
        builder.append(" tasks in your list.");
        if (tasks.isEmpty()) {
            builder.append("\nCongratulations!");
        }

        return builder.toString();

    }

    /**
     * Marks an item in the list as done.
     * @param index The index of the Task in the list to be marked.
     *              This is a number from 1 to the size of the list, or null if all items should be
     *              marked.
     * @return A message to be displayed to the user as acknowledgement and indication of successful
     *         execution.
     * @throws OutOfRangeException If the index provided is 0 or less, or greater than the size of
     *         the list.
     * @throws AlreadyMarkedException If the item to be marked is already marked.
     */
    public String mark(Integer index) throws OutOfRangeException, AlreadyMarkedException {

        if (index == null) {
            for (Task task : tasks) {
                try {
                    task.markDone();
                } catch (AlreadyMarkedException ignored) {
                    /*All AlreadyMarkedExceptions here can be safely ignored, as they are custom
                    exceptions used to indicate to the user that they're marking a marked task.
                    We don't want to display a bunch of exceptions in this case.*/
                }
            }
            return "All done.";
        }

        int i = index - 1;

        if (i < 0 || i >= tasks.size()) {
            Task task = tasks.get(i);
            task.markDone();
            return "Mission accomplished.\n" + task.toString();
        } else {
            throw new OutOfRangeException();
        }

    }

    /**
     * Marks an item in the list as not yet done.
     * @param index The index of the Task in the list to be unmarked.
     *              This is a number from 1 to the size of the list, or null if all items should be
     *              unmarked.
     * @return A message to be displayed to the user as acknowledgement and indication of successful
     *         execution.
     * @throws OutOfRangeException If the index provided is 0 or less, or greater than the size of
     *         the list.
     * @throws AlreadyUnmarkedException If the item to be unmarked is already unmarked.
     */
    public String unmark(Integer index) throws OutOfRangeException, AlreadyUnmarkedException {

        if (index == null) {
            for (Task task : tasks) {
                try {
                    task.markUndone();
                } catch (AlreadyUnmarkedException ignored) {
                    /*All AlreadyUnmarkedExceptions here can be safely ignored, as they are custom
                    exceptions used to indicate to the user that they're unmarking an unmarked task.
                    We don't want to display a bunch of exceptions in this case.*/
                }
            }
            return "All undone.";
        }

        int i = index - 1;

        if (i < 0 || i >= tasks.size()) {
            Task task = tasks.get(i);
            task.markUndone();
            return "Uncharacteristic of you. More work has been added to the pile.\n" + task.toString();
        } else {
            throw new OutOfRangeException();
        }

    }

    /**
     * Returns a message depending on whether the list is empty. For personality only.
     * @return A preset message.
     */
    public String docRest() {
        if (tasks.isEmpty()) {
            return "How'd you even sleep while standing up... well, have a good rest.";
        } else {
            return "There's still lots of work that needs to be done. "
                    + "We can't afford to have you resting.";
        }
    }

    /**
     * Returns the number of items in the list.
     * @return The number of items in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns a string representation of the task list.
     * @return the string representation of the task list.
     */
    @Override
    public String toString() {

        if (tasks.isEmpty()) {
            return "...\n" + "You don't have any tasks. Enjoy your day off.";
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }

        return output.toString();

    }

    /**
     * Searches through the list for all tasks containing the given string.
     * @param searchTerm The term(s) to search for.
     * @return A list of the tasks that contain the search term.
     */
    public String find(String searchTerm) {
        assert searchTerm != null : "The search term should not be null!";
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            if (!task.contains(searchTerm)) {
                continue;
            }
            stringBuilder.append(task.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
