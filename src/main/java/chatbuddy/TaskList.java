package chatbuddy;

import java.util.ArrayList;
import java.util.stream.Collectors;

import chatbuddy.task.Task;

/**
 * The TaskList represents a list of tasks.
 * Tasks can be added or delete from the list.
 * Tasks in the list can be mark as done or not done.
 */
public class TaskList {
    /** The list of tasks. */
    private ArrayList<Task> tasks;

    /** Returns an instance of a TaskList that has no tasks. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns an instance of a TaskList with the list of inputted tasks.
     *
     * @param tasks The tasks that the TaskList should contain.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks if the task number is valid.
     * The task number is valid if it is within the range of available task.
     * The task number is invalid if the task number exceeds the number of tasks.
     *
     * @param taskNum The task number to check for.
     * @throws ChatBuddyException If the task number is invalid.
     */
    private void checkValidTaskNumber(int taskNum) throws ChatBuddyException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new ChatBuddyException("Please input a valid task number. There are only "
                    + getSize() + " tasks in the list.");
        }
    }

    /**
     * Adds a task to the back of the task list.
     *
     * @param task The task to add to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        assert(tasks.get(tasks.size() - 1).equals(task));
    }

    /**
     * Deletes the task with the given task number from the task list.
     *
     * @param taskNum The task number of the task to delete.
     * @return The task object that is deleted.
     * @throws ChatBuddyException If the task number is invalid.
     */
    public Task deleteTask(int taskNum) throws ChatBuddyException {
        // check for valid task number
        checkValidTaskNumber(taskNum);

        // delete task from list
        int taskIndex = taskNum - 1;
        Task task = tasks.remove(taskIndex);
        assert(!tasks.contains(task));
        return task;
    }

    /**
     * Returns the task with the given task number.
     *
     * @param taskNum The task number of the task to get.
     * @return The task object with the given task number.
     * @throws ChatBuddyException If the task number is invalid.
     */
    private Task getTask(int taskNum) throws ChatBuddyException {
        // check for valid task number
        checkValidTaskNumber(taskNum);
        assert(taskNum > 0 && taskNum <= tasks.size());

        int taskIndex = taskNum - 1;
        return tasks.get(taskIndex);
    }

    /**
     * Marks the task with the given task number as done.
     *
     * @param taskNum The task number of the task to mark as done.
     * @return The task that was marked as done.
     * @throws ChatBuddyException If the task number is invalid.
     */
    public Task markTaskAsDone(int taskNum) throws ChatBuddyException {
        Task task = getTask(taskNum);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task with the given task number as not done.
     *
     * @param taskNum The task number of the task to mark as not done.
     * @return The task that was marked as not done.
     * @throws ChatBuddyException If the task number is invalid.
     */
    public Task markTaskAsNotDone(int taskNum) throws ChatBuddyException {
        Task task = getTask(taskNum);
        task.markAsNotDone();
        return task;
    }

    /**
     * Changes the tag of the task with the given task number to the given tag.
     *
     * @param taskNum The task number of the task to change the tag.
     * @param tag The tag to change to.
     * @return The task which tag was changed.
     * @throws ChatBuddyException If the task number is invalid.
     */
    public Task updateTaskTag(int taskNum, String tag) throws ChatBuddyException {
        Task task = getTask(taskNum);
        task.updateTag(tag);
        return task;
    }

    /**
     * Returns a list of strings representing the task in the format for saving.
     *
     * @return A list of strings representing the task in the format for saving.
     */
    public ArrayList<String> getTaskStringsToSave() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            String taskString = task.getInformationForSaving();
            taskStrings.add(taskString);
        }
        return taskStrings;
    }

    /**
     * Returns a list of strings representing the task in the format for printing.
     *
     * @return A list of strings representing the task in the format for printing.
     */
    public ArrayList<String> getTaskStringsToPrint() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task task : tasks) {
            taskStrings.add(task.toString());
        }
        return taskStrings;
    }

    /**
     * Finds a list of tasks containing the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A TaskList containing a list of tasks that contains the given keyword.
     */
    public TaskList getMatchingTasks(String keyword) {
        return new TaskList(tasks.stream()
                .filter(task -> task.containKeyword(keyword))
                .collect(Collectors.toCollection(ArrayList::new)));
    }
}
