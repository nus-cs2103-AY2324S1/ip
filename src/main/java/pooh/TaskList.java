package pooh;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a list of tasks.
 * <p>
 * This class encapsulates the list of tasks and provides methods for adding,
 * removing, and retrieving tasks.
 * </p>
 */
public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructs a new TaskList with the given list of tasks.
     *
     * @param taskList The initial list of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param task The index of the task to be removed.
     */
    public void remove(int task) {
        this.taskList.remove(task);
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws InvalidTaskException If the index is out of bounds.
     */
    public Task getTask(int index) throws InvalidTaskException {
        try {
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Adds a task to the task list based on user input.
     *
     * @param taskList   The task list to which the task will be added.
     * @param userAction The type of task to be added.
     * @param cmd        The full command from the user.
     * @throws EmptyTaskDescriptorsException If the task description is empty.
     */
    public static void addTask(TaskList taskList, String userAction, String cmd) throws
            EmptyTaskDescriptorsException {
        if (cmd.split(" ", 2).length == 1) {
            throw new EmptyTaskDescriptorsException();
        }
        String userArgs = cmd.split(" ", 2)[1];
        Task task;
        if (userAction.equalsIgnoreCase("todo")) {
            task = new Todo(userArgs);
        } else if (userAction.equalsIgnoreCase("deadline")) {
            String[] specificArgs = userArgs.split(" /by ", 2);
            String description = specificArgs[0];
            String deadlineTime = specificArgs[1];
            task = new Deadline(description, deadlineTime);
        } else {
            String[] specificArgs = userArgs.split(" /from ", 2);
            String description = specificArgs[0];
            String[] eventTimes = specificArgs[1].split(" /to ");
            String eventStartTime = eventTimes[0];
            String eventEndTime = eventTimes[1];
            task = new Event(description, eventStartTime, eventEndTime);
        }
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list at the specified index.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param index    The index of the task to delete.
     * @throws InvalidTaskException If the index is out of bounds.
     */
    public static void deleteTask(TaskList taskList, int index) throws InvalidTaskException {
        Task task = taskList.getTask(index);
        taskList.remove(index);
    }

    /**
     * Filters tasks in the list based on a keyword.
     *
     * @param keyword The keyword to search for in the task descriptions.
     * @return A new TaskList containing tasks whose descriptions contain the keyword.
     */
    public TaskList getTasksWithKeyword(String keyword) {
        List<Task> keywordTasks = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.ifDescriptionContains(keyword)) {
                keywordTasks.add(task);
            }
        }
        return new TaskList(keywordTasks);
    }

    /**
     * Postpones a time-sensitive task to a new specified deadline.
     *
     * @param postponeIndex  The index of the task to be postponed.
     * @param deadlineString The new deadline to which the task should be postponed.
     * @return The postponed task.
     * @throws InvalidTaskException          If the provided index does not correspond to a valid task.
     * @throws NotTimeSensitiveTaskException If the task at the given index is not time-sensitive.
     */
    public Task postponeTimeSensitiveTask(int postponeIndex, String deadlineString) throws InvalidTaskException,
            NotTimeSensitiveTaskException {
        Task task = getTask(postponeIndex);
        if (!(task instanceof TimeSensitiveTask)) {
            throw new NotTimeSensitiveTaskException();
        }
        TimeSensitiveTask postponedTask = (TimeSensitiveTask) task;
        postponedTask.postpone(deadlineString);
        return postponedTask;
    }
}
