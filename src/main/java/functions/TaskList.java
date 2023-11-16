package functions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.FixedDuration;
import tasks.Task;
import tasks.ToDo;

/**
 * Manages a list of tasks and provides methods for task manipulation and retrieval.
 */
public class TaskList {

    /**
     * The ArrayList that stores the tasks.
     */
    protected ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object using task details from a list of strings.
     *
     * @param taskDetails The list of strings containing task details.
     */
    public TaskList(ArrayList<String> taskDetails) {
        this.tasks = new ArrayList<>();
        for (String details : taskDetails) {
            handleLoadTask(details);
        }
    }

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads a task from the provided input and adds it to the task list.
     *
     * @param input The input string representing the task details.
     */
    private void handleLoadTask(String input) {
        Task t = null;
        if (input.startsWith("[D]")) {
            t = loadDeadlineTask(input);
        }
        if (input.startsWith("[T]")) {
            String desc = input.substring(7);
            t = new ToDo(desc);
        }
        if (input.startsWith("[E]")) {
            t = loadEventTask(input);
        }
        if (input.startsWith("[F]")) {
            t = loadFixedDurationTask(input);
        }
        assert t != null : "File has been corrupted";
        if (input.charAt(4) == 'X') {
            t.markAsDone();
        }
        tasks.add(t);
    }

    private Task loadDeadlineTask(String input) {
        int y = input.indexOf("(by: ");
        String desc = input.substring(7, y - 1);
        int end = input.indexOf(")");
        String by = input.substring(y + 5, end);
        LocalDateTime date = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return new Deadline(desc, date);
    }

    private Task loadEventTask(String input) {
        int fromIndex = input.indexOf("(from: ");
        int toIndex = input.indexOf("to: ");
        String desc = input.substring(7, input.indexOf("(") - 1);
        String s = input.substring(fromIndex + 7, toIndex - 1).trim();
        String e = input.substring(toIndex + 4, input.indexOf(")")).trim();
        LocalDateTime start = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        LocalDateTime end = LocalDateTime.parse(e, DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
        return new Event(desc, start, end);
    }

    private Task loadFixedDurationTask(String input) {
        int y = input.indexOf("(for: ");
        String desc = input.substring(7, y - 1);
        int d = input.indexOf(")");
        String duration = input.substring(y + 6, d);
        return new FixedDuration(desc, duration);
    }

    /**
     * Creates a new task based on the provided description and date-time information.
     *
     * @param desc   The description of the task.
     * @param first  The first LocalDateTime parameter (start or due date).
     * @param second The second LocalDateTime parameter (end date for event).
     * @return The created Task object.
     */
    public Task createTask(String desc, String duration, LocalDateTime first, LocalDateTime second) {
        Task t = null;
        t = new ToDo(desc);
        if (duration != null) {
            t = new FixedDuration(desc, duration);
        }
        if (second != null) {
            t = new Event(desc, first, second);
        } else if (first != null) {
            t = new Deadline(desc, first);
        }
        this.tasks.add(t);
        return t;
    }

    /**
     * Marks a task as completed.
     *
     * @param num The index of the task to be marked as completed.
     * @return The marked Task object.
     */
    public Task markTask(int num) {
        Task t = this.tasks.get(num - 1);
        t.markAsDone();
        return t;
    }

    /**
     * Marks a completed task as not completed.
     *
     * @param num The index of the task to be marked as not completed.
     * @return The unmarked Task object.
     */
    public Task unmarkTask(int num) {
        Task t = this.tasks.get(num - 1);
        t.unmarkAsDone();
        return t;
    }


    /**
     * Deletes a task from the task list.
     *
     * @param num The index of the task to be deleted.
     * @return The deleted Task object.
     */
    public Task deleteTask(int num) {
        return this.tasks.remove(num - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int numOfTasks() {
        return this.tasks.size();
    }

    /**
     * Returns an ArrayList containing all tasks in the list.
     *
     * @return An ArrayList containing all tasks in the list.
     */
    public ArrayList<Task> showList() {
        return this.tasks;
    }

    /**
     * Searches for tasks containing the specified description.
     *
     * @param description The description to search for.
     * @return An ArrayList of tasks matching the search description.
     */
    public ArrayList<Task> searchTask(String description) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task match : this.tasks) {
            if (match.getDescription().contains(description)) {
                results.add(match);
            }
        }
        return results;
    }
}
