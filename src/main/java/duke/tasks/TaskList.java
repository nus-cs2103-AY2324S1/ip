package duke.tasks;

import java.util.ArrayList;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.exceptions.DukeInvalidArgumentException;
import duke.ui.CliUi;

/**
 * The task list is used to store the user's tasks.
 */
public class TaskList {

    private static final String INVALID_TASK_INDEX_ERROR = "You've specified an invalid task number."
            + "Check your task list again with the \"list\" command.";

    /** The list that is used to store the user's tasks. */
    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets task at specified index.
     *
     * @param index The index of the specified item.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param type       The type of task to be added.
     * @param taskString Information about the task to be added.
     * @return Duke's response to the user.
     * @throws DukeInvalidArgumentException If the given taskString is invalid.
     */
    public String addTask(TaskType type, String taskString) throws DukeInvalidArgumentException {
        switch (type) {
        case TODO:
            if (taskString.equals("")) {
                throw new DukeInvalidArgumentException("You didn't specify a task to do. "
                        + "Check that you're doing \"todo {description}\".");
            }
            this.tasks.add(new ToDo(taskString));
            break;

        case DEADLINE:
            try {
                String[] deadlineParts = taskString.split(" /by ", 2);
                this.tasks.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeInvalidArgumentException(
                        "Your deadline seems to be formatted wrongly. "
                                + "Check that you're doing: \"deadline {description} /by {yyyy-MM-dd HH:mm}\".");
            }
            break;

        case EVENT:
            try {
                String[] eventParts = taskString.split(" /from ", 2);
                String description = eventParts[0].trim();
                String[] eventTimeParts = eventParts[1].trim().split(" /to ", 2);
                this.tasks.add(new Event(description, eventTimeParts[0].trim(), eventTimeParts[1].trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeInvalidArgumentException(
                        "Your event seems to be formatted wrongly. "
                                + "Check that you're doing: \"event {description} /from {yyyy-MM-dd HH:mm} "
                                + "/to {yyyy-MM-dd HH:mm}\".");
            }
            break;

        default:
            throw new DukeInvalidArgumentException("I'm gonna be honest, no idea what you're saying.");
        }

        CliUi.printlns(new String[] {
            "Got it. I've added this task:",
            this.tasks.get(this.tasks.size() - 1).toString(),
            "Now you have " + this.tasks.size() + " tasks in the list."
        });
        return String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.",
                this.tasks.get(this.tasks.size() - 1), this.tasks.size());
    }

    /**
     * Marks specified task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return Duke's response to the user.
     * @throws DukeInvalidArgumentException If the index is invalid.
     */
    public String markTaskDone(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeInvalidArgumentException(INVALID_TASK_INDEX_ERROR);
        }

        this.tasks.get(index - 1).markDone();
        CliUi.printlns(new String[] { "Nice! I've marked this task as done:", this.tasks.get(index - 1).toString() });
        return String.format("Nice! I've marked this task as done:%s", this.tasks.get(index - 1));
    }

    /**
     * Unmarks specified task as not done.
     *
     * @param index The index of the task to be unmarked as not done.
     * @return Duke's response to the user.
     * @throws DukeInvalidArgumentException If the index is invalid.
     */
    public String unmarkTaskDone(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeInvalidArgumentException(INVALID_TASK_INDEX_ERROR);
        }

        this.tasks.get(index - 1).unmarkDone();
        CliUi.printlns(
                new String[] { "OK, I've marked this task as not done yet:", this.tasks.get(index - 1).toString() });
        return String.format("OK, I've marked this task as not done yet:%s", this.tasks.get(index - 1));
    }

    /**
     * Deletes specified task.
     *
     * @param index The index of the task to be deleted.
     * @return Duke's response to the user.
     * @throws DukeInvalidArgumentException If the index is invalid.
     */
    public String deleteTask(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.tasks.size()) {
            throw new DukeInvalidArgumentException(INVALID_TASK_INDEX_ERROR);
        }

        CliUi.printlns(new String[] { "Noted. I've removed this task:", this.tasks.get(index - 1).toString() });
        String output = String.format("Noted. I've removed this task:%s", this.tasks.get(index - 1));
        this.tasks.remove(index - 1);
        return output;
    }

    /**
     * Lists all tasks.
     *
     * @return Duke's response to the user.
     */
    public String listTasks() {
        if (this.tasks.isEmpty()) {
            CliUi.println("You have no tasks so far.");
            return "You have no tasks so far.";
        }

        String[] lines = new String[1 + this.tasks.size()];
        lines[0] = "Here are the tasks in your list:";
        for (int i = 0; i < this.tasks.size(); i++) {
            lines[i + 1] = (i + 1) + ". " + this.tasks.get(i);
        }

        CliUi.printlns(lines);
        return String.join("\n", lines);
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Find tasks that contain a specified query in their description.
     *
     * @param query A query specified by the user.
     * @return An ArrayList containing the index of items that contain the specified
     *         query.
     */
    public ArrayList<Integer> findTasks(String query) {
        String loweredQuery = query.toLowerCase();

        IntPredicate indexContainsLoweredQuery = i -> this.tasks.get(i)
                .getDescription().toLowerCase().contains(loweredQuery);

        return IntStream.range(0, this.tasks.size())
                .filter(indexContainsLoweredQuery)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
