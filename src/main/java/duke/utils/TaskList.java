package duke.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.DukeException;
import duke.DukeMissingTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList object.
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the size of the list of tasks.
     * @return The size of the list of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Marks a task as done.
     * @param taskNumber The task number of the task to be marked as not done.
     */
    public Task unmarkTask(int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markAsUndone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingTaskException();
        }
    }

    /**
     * Marks a task as not done.
     * @param taskNumber The task number of the task to be marked as done.
     */
    public Task markTask(int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingTaskException();
        }
    }

    /**
     * Adds a todo to the list of tasks.
     * @param description The description of the todo.
     * @return The todo that is added to the list of tasks.
     */
    public Todo addTodo(String description) {
        Todo todo = new Todo(description);
        tasks.add(todo);

        return todo;
    }

    /**
     * Adds a deadline to the list of tasks.
     * @param description The description of the deadline.
     * @param dateTime The deadline of the deadline.
     * @return The deadline that is added to the list of tasks.
     */
    public Deadline addDeadline(String description, LocalDateTime dateTime) {
        Deadline deadline = new Deadline(description, dateTime);
        tasks.add(deadline);

        return deadline;
    }

    /**
     * Adds an event to the list of tasks.
     * @param description The description of the event.
     * @param start The start of the event.
     * @param end The end of the event.
     * @return The event that is added to the list of tasks.
     */
    public Event addEvent(String description, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(description, start, end);
        tasks.add(event);

        return event;
    }

    /**
     * Deletes a task from the list of tasks.
     * @param taskNumber The task number of the task to be deleted.
     * @return The task that is deleted from the list of tasks.
     * @throws DukeException If the task number is not an integer or if the task number is out of bounds.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        try {
            Task task = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingTaskException();
        }
    }

    /**
     * Returns the list of tasks that contains the keyword.
     * @param searchTerm The keyword to be searched.
     * @return The list of tasks that contains the keyword.
     */
    public TaskList filterTasks(String searchTerm) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                filteredTasks.add(task);
            }
        }
        return new TaskList(filteredTasks);
    }

    /**
     * Returns the list of tasks that contains the keyword.
     * @return The list of tasks that contains the keyword.
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            message.append("\t").append(i++).append(". ").append(task).append("\n");
        }
        return message.toString();
    }
}
