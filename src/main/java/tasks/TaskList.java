package tasks;

import java.lang.StringBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.InvalidIndexException;

/**
 * Represents a list of all user tasks.
 */
public class TaskList {
    protected static final DateTimeFormatter DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected ArrayList<Task> tasks;

    /**
     * Constructor, initializes array list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a todo task.
     *
     * @param desc Description of task.
     * @param isMarked If task is marked, 1, else, 0.
     * @return A todo task.
     */
    public Task addTodo(String desc, int isMarked) {
        Task task = new TodoTask(desc, isMarked);
        this.tasks.add(task);
        return task;
    }

    /**
     * Creates a deadline task.
     *
     * @param desc Description of task.
     * @param deadline Deadline date/time of task.
     * @param isMarked If task is marked, 1, else, 0.
     * @return A deadline task.
     * @throws DateTimeParseException If deadline doesn't have format "yyyy-MM-dd HHmm".
     */
    public Task addDeadline(String desc, String deadline, int isMarked)
            throws DateTimeParseException {
        LocalDateTime dateTime = LocalDateTime.parse(deadline, DATETIME_FORMAT);
        Task task = new DeadlineTask(desc, dateTime, isMarked);
        this.tasks.add(task);
        return task;
    }

    /**
     * Creates an event task.
     *
     * @param desc Description of task.
     * @param start Start date/time of task.
     * @param end End date/time of task.
     * @param isMarked If task is marked, 1, else, 0.
     * @return An event task.
     * @throws DateTimeParseException If start/end doesn't have format "yyyy-MM-dd HHmm".
     */
    public Task addEvent(String desc, String start, String end, int isMarked)
            throws DateTimeParseException {
        LocalDateTime dateTimeStart = LocalDateTime.parse(start, DATETIME_FORMAT);
        LocalDateTime dateTimeEnd = LocalDateTime.parse(end, DATETIME_FORMAT);
        Task task = new EventTask(desc, dateTimeStart, dateTimeEnd, isMarked);
        this.tasks.add(task);
        return task;
    }

    /**
     * Marks task as done.
     *
     * @param i Index of task to be marked done.
     * @return Task which was marked done.
     */
    public Task markTaskAsDone(int i) {
        if (this.tasks.size() < 1) {
            throw new DukeException("The task list is empty.");
        }
        if (i < 1 || i > this.tasks.size()) {
            throw new InvalidIndexException(this.tasks.size());
        }
        Task task = this.tasks.get(i - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks task as not done.
     *
     * @param i Index of task to be marked not done.
     * @return Task which was marked not done.
     */
    public Task unmarkTask(int i) {
        if (this.tasks.size() < 1) {
            throw new DukeException("The task list is empty.");
        }
        if (i < 1 || i > this.tasks.size()) {
            throw new InvalidIndexException(this.tasks.size());
        }
        Task task = this.tasks.get(i - 1);
        task.unmark();
        return task;
    }

    /**
     * Deletes a task.
     *
     * @param i Index of task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int i) {
        if (this.tasks.size() < 1) {
            throw new DukeException("The task list is empty.");
        }
        if (i < 1 || i > this.tasks.size()) {
            throw new InvalidIndexException(this.tasks.size());
        }
        return this.tasks.remove(i - 1);
    }

    /**
     * Returns size of task list.
     *
     * @return Size of task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task list in the relevant format for storing in data file.
     *
     * @return The task list string with the relevant format.
     */
    public String getTextFormattedString() {
        StringBuilder str = new StringBuilder();
        for (Task task : this.tasks) {
            str.append(task.getTextFormattedString() + "\n");
        }
        return str.toString();
    }

    /**
     * Returns string representation of filtered task list.
     *
     * @return String representation of filtered task list.
     */
    public String getMatchingTasks(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.desc.contains(keyword)) {
                filteredTasks.add(task);
            }
        }
        if (filteredTasks.size() == 0) {
            return "No task matches the given keyword.";
        }

        StringBuilder str = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 1; i <= filteredTasks.size(); i++) {
            str.append(i + ". " + filteredTasks.get(i - 1).toString() + "\n");
        }
        return str.toString();
    }

    /**
     * Returns string representation of task list.
     *
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int len = this.tasks.size();
        if (len == 0) {
            return "The task list is empty.";
        }
        str.append("Here are the tasks in your list:\n");
        for (int i = 1; i < len; i++) {
            str.append(i + ". " + this.tasks.get(i - 1) + "\n");
        }
        str.append(len + ". " + this.tasks.get(len - 1));
        return str.toString();
    }

}
