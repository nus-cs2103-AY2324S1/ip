package iris;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Iris application.
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructor for the ToDoList class.
     *
     * @param list The initial list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes a task from the list by index.
     *
     * @param index The index of the task to be removed.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void remove(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.remove(index - 1);
    }

    /**
     * Gets a task from the list by index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task getTask(int index) throws InvalidTaskException {
        try {
            return list.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Marks a task as done by index.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void markTask(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.get(index - 1).markAsDone();
    }

    /**
     * Marks a task as undone by index.
     *
     * @param index The index of the task to be marked as undone.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void unmarkTask(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.get(index - 1).markAsUndone();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }

    public static void addTask(TaskList taskList, String command, String description)
            throws EmptyTaskDescriptorsException {
        if (description.isEmpty()) {
            throw new EmptyTaskDescriptorsException();
        }
        Task task = null;
        if (command.equalsIgnoreCase("todo")) {
            task = new Todo(description);
        } else if (command.equalsIgnoreCase("deadline")) {
            String[] deadlineSections = description.split(" /by ", 2);
            String name = deadlineSections[0];
            String deadlineString = deadlineSections[1];
            task = new Deadline(name, deadlineString);
        } else if (command.equalsIgnoreCase("event")) {
            String[] eventSections = description.split(" /from ", 2);
            String name = eventSections[0];
            String startAndEnd = eventSections[1];
            String[] startAndEndSections = startAndEnd.split(" /to ", 2);
            String startTime = startAndEndSections[0];
            String endTime = startAndEndSections[1];
            task = new Event(name, startTime, endTime);
        }
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        Ui.printLength(taskList);
    }

    /**
     * Deletes a task from the ToDoList by index.
     *
     * @param taskList The ToDoList from which the task will be deleted.
     * @param index    The index of the task to be deleted.
     */
    public static void deleteTask(TaskList taskList, int index) throws InvalidTaskException {
        Task task = taskList.getTask(index);
        taskList.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        Ui.printLength(taskList);
    }

    public TaskList getTasksWithKeyword(String keyword) {
        ArrayList<Task> keywordTasks = new ArrayList<Task>();
        for (Task task : list) {
            if (task.ifDescriptionContains(keyword)) {
                keywordTasks.add(task);
            }
        }
        return new TaskList(keywordTasks);
    }

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

    /**
     * Overrides the default toString method to provide a custom string representation of the ToDoList.
     *
     * @return A formatted string representing the list of tasks.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append((i + 1) + ". " + list.get(i));
            if (i + 1 < list.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}