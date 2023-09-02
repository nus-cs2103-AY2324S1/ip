package duke.task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.error.DukeException;
import duke.parser.Validate;


/**
 * Represents a list of tasks that can be managed, including adding, deleting, marking, and unmarking tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from serialized tasks.
     *
     * @param serializedTasks Serialized tasks in the form of strings.
     * @throws DukeException If there's an issue parsing the serialized tasks.
     */
    public TaskList(ArrayList<String> serializedTasks) throws DukeException {
        this.tasks = new ArrayList<>();

        for (String serializedTask : serializedTasks) {
            String[] tokens = serializedTask.split(" \\| ");
            String taskType = tokens[0];
            boolean status = tokens[1].equals("1");
            String description = tokens[2];

            Task task = null;
            switch (taskType) {
            case "T": {
                task = this.addTodo(description);
                break;
            }
            case "D": {
                task = this.addDeadline(description, tokens[3]);
                break;
            }
            case "E": {
                task = this.addEvent(description, tokens[3], tokens[4]);
                break;
            }
            default:
                throw new DukeException("Unknown serialized task");
            }
            if (task != null && status) {
                task.markDone();
            }
        }
    }

    /**
     * Validates and retrieves the real index from the given task index.
     *
     * @param index Task index to be validated.
     * @return Validated real index of the task.
     * @throws DukeException If the provided index is invalid.
     */
    private int validateIndex(int index) throws DukeException {
        if (index < 1) {
            throw new DukeException("Please provide a task index that is 1 <= task index <= 100.");
        } else if (index > this.tasks.size()) {
            throw new DukeException("The given task index is higher than current task list: "
                    + this.tasks.size() + ".");
        }

        int realIndex = index - 1;
        Task task = tasks.get(realIndex);
        if (task == null) {
            throw new DukeException("There is no task at the given task index.");
        }
        return realIndex;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     * @return The added task.
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        return task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index Index of the task to be deleted.
     * @return The deleted task.
     * @throws DukeException If the provided index is invalid.
     */
    public Task deleteTask(int index) throws DukeException {
        return this.tasks.remove(this.validateIndex(index));
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked.
     * @return The marked task.
     * @throws DukeException If the provided index is invalid.
     */
    public Task markTask(int index) throws DukeException {
        Task task = this.tasks.get(this.validateIndex(index));
        task.markDone();
        return task;
    }

    /**
     * Unmarks a task (marks it as not done).
     *
     * @param index Index of the task to be unmarked.
     * @return The unmarked task.
     * @throws DukeException If the provided index is invalid.
     */
    public Task unmarkTask(int index) throws DukeException {
        Task task = this.tasks.get(this.validateIndex(index));
        task.unmarkDone();
        return task;
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param desc Description of the todo task.
     * @return The added todo task.
     */
    public Todo addTodo(String desc) {
        Todo todo = new Todo(desc);
        this.addTask(todo);
        return todo;
    }

    /**
     * Adds a deadline task to the task list.
     *
     * @param desc Description of the deadline task.
     * @param by   Deadline of the task.
     * @return The added deadline task.
     * @throws DukeException If there's an issue with the deadline format.
     */
    public Deadline addDeadline(String desc, String by) throws DukeException {
        Deadline deadline = new Deadline(desc, Validate.validateLocalDateTime(by));
        this.addTask(deadline);
        return deadline;
    }

    /**
     * Adds an event task to the task list.
     *
     * @param desc Description of the event task.
     * @param from Start time of the event.
     * @param to   End time of the event.
     * @return The added event task.
     * @throws DukeException If there's an issue with the date-time formats.
     */
    public Event addEvent(String desc, String from, String to) throws DukeException {
        Event event = new Event(desc, Validate.validateLocalDateTime(from), Validate.validateLocalDateTime(to));
        this.addTask(event);
        return event;
    }

    /**
     * Saves the tasks in the task list to a BufferedWriter.
     *
     * @param writer BufferedWriter to write the tasks to.
     * @throws IOException If there's an issue writing to the writer.
     */
    public void save(BufferedWriter writer) throws IOException {
        for (Task task : this.tasks) {
            writer.write(task.serialize());
            writer.newLine();
        }
    }

    /**
     * Returns a summary of the number of tasks in the task list.
     *
     * @return Summary of the number of tasks.
     */
    public String status() {
        return String.format("You have %d tasks in the list.", this.tasks.size());
    }

    /**
     * Retrieves a list of tasks containing a given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that match the keyword.
     */
    public String searchMatchingTasks(String keyword) {
        int count = 0;
        StringBuilder str = new StringBuilder("Here are the matching tasks in your list:");
        for (Task task : this.tasks) {
            if (task.containKeyword(keyword)) {
                str.append(count > 0 ? "\n" : "").append(count++).append(".").append(task);
            }
        }
        return count > 0 ? str.toString() : "No matching results for search: " + keyword;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return String representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(this.status());
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(i + 1).append(".").append(this.tasks.get(i)).append(
                    i < this.tasks.size() - 1 ? "\n" : "");
        }
        return str.toString();
    }
}
