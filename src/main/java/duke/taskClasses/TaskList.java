package duke.taskClasses;

import duke.exception.InvalidDateTimeException;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to manipulate and interact with the list.
 */
public class TaskList {

    /** List of tasks */
    private ArrayList<Task> taskLists;

    /** Count of tasks in the list */
    private int taskCount = 0;

    /**
     * Constructs a new TaskList with an existing list of tasks.
     *
     * @param taskLists List of tasks.
     */
    public TaskList(ArrayList<Task> taskLists) {
        this.taskLists = taskLists;
    }

    /**
     * Constructs a new empty TaskList.
     */
    public TaskList() {
        this.taskLists = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return this.taskLists;
    }

    /**
     * Prints the status and description of all tasks in the list.
     */
    public void printAllStatusAndDescription() {
        for (int i = 0; i < taskLists.size(); i++) {
            Task taskToPrint = taskLists.get(i);
            System.out.println((i + 1) + ". " + taskToPrint.getStatusAndDescription());
        }
    }

    /**
     * Returns the status and description of the task at the given index.
     *
     * @param number The index of the task in the list (1-based).
     * @return String representation of the task status and description.
     */
    public String getStatusAndDescription(int number) {
        return taskLists.get(number - 1).getStatusAndDescription();
    }

    /**
     * Returns the count of tasks in the list.
     *
     * @return The task count.
     */
    public int getTasksCount() {
        return this.taskCount;
    }

    /**
     * Adds a ToDo task to the list.
     *
     * @param isDone The completion status of the task.
     * @param description Description of the task.
     */
    public void addToDoToList(Boolean isDone, String description) {
        Task newTask = new ToDo(description);
        if (isDone) {
            newTask.markAsDone();
        }
        taskLists.add(newTask);
        this.taskCount++;
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param isDone The completion status of the task.
     * @param description Description of the task.
     * @param date Due date of the task.
     * @throws InvalidDateTimeException If the provided date string is not in a valid format.
     */
    public void addDeadlineToList(Boolean isDone, String description, String date) throws InvalidDateTimeException {
        Task newTask = new Deadline(description, date);
        if (isDone) {
            newTask.markAsDone();
        }
        taskLists.add(newTask);
        this.taskCount++;
    }

    /**
     * Adds an Event task to the list.
     *
     * @param isDone The completion status of the task.
     * @param description Description of the task.
     * @param start Start date and time of the event.
     * @param end End date and time of the event.
     * @throws InvalidDateTimeException If the provided date strings are not in a valid format.
     */
    public void addEventToList(Boolean isDone, String description, String start, String end) throws InvalidDateTimeException {
        Task newTask = new Event(description, start, end);
        if (isDone) {
            newTask.markAsDone();
        }
        taskLists.add(newTask);
        this.taskCount++;
    }

    /**
     * Clears the task count. Note: This does not clear the task list itself.
     */
    public void clear() {
        this.taskCount = 0;
    }

    /**
     * Deletes the task at the given index and returns its details.
     *
     * @param number The index of the task to delete (1-based).
     * @return The string representation of the deleted task.
     */
    public String deleteTask(int number) {
        String content = taskLists.get(number - 1).getStatusAndDescription();
        taskLists.remove(number - 1);
        this.taskCount--;
        return content;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param number The index of the task to mark as done (1-based).
     */
    public void markTaskAsDone(int number) {
        taskLists.get(number - 1).markAsDone();
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param number The index of the task to mark as not done (1-based).
     */
    public void markTaskAsNotDone(int number) {
        taskLists.get(number - 1).markAsNotDone();
    }

    /**
     * Prints the status and description of all tasks in the list that contains the keyword in description.
     */
    public void printAllStatusAndDescriptionWithKeyword (String keyword) {
        for (int i = 0; i < taskLists.size(); i++) {
            Task taskToPrint = taskLists.get(i);
            if (taskToPrint.description.contains(keyword)) {
                System.out.println((i + 1) + ". " + taskToPrint.getStatusAndDescription());
            }
        }
    }
}
