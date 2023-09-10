package duke.utils;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks in the Duke application
 * and provides methods to manage and manipulate these tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Enumeration representing various command types for task manipulation.
     */
    enum Type {
        MARK("mark"),
        UNMARK("unmark"),
        LIST("list"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find"),
        NOTFOUND("");

        private final String name;

        private Type(String name) {
            this.name = name;
        }

        /**
         * Retrieves the Type enum based on its name.
         *
         * @param name The name of the Type enum.
         * @return The Type enum corresponding to the given name.
         */
        protected static Type of(String name) {
            for (Type type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            return NOTFOUND;
        }
    }

    /**
     * Constructs a new TaskList object with an initial list of tasks.
     *
     * @param tasks The initial list of tasks.
     */
    protected TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Converts the list of tasks to a list of CSV strings.
     *
     * @return A list of CSV strings representing the tasks.
     */
    protected ArrayList<String> csvArray() {
        ArrayList<String> csv = new ArrayList<>();
        for (Task task : this.tasks) {
            csv.add(task.toCsv());
        }
        return csv;
    }

    /**
     * Executes a user command and returns a response.
     *
     * @param input   The user input command.
     * @param command The command keyword extracted from the input.
     * @return A Response object representing the response to the command.
     * @throws DukeException if there's an error executing the command.
     */
    protected Response execute(String input, String command) throws DukeException {
        Task task;

        switch (Type.of(command)) {
        case TODO:
            task = new Todo(Command.assertString(input, command));
            break;
        case DEADLINE:
            task = new Deadline(
                Command.assertString(input, command),
                Command.assertDateTime(input, "by")
            );
            break;
        case EVENT:
            task = new Event(
                Command.assertString(input, command),
                Command.assertDateTime(input, "from"),
                Command.assertDateTime(input, "to")
            );
            break;
        case MARK:
            return this.mark(Command.assertInteger(input, command));
        case UNMARK:
            return this.unmark(Command.assertInteger(input, command));
        case DELETE:
            return this.delete(Command.assertInteger(input, command));
        case LIST:
            return this.list();
        case FIND:
            return this.find(Command.assertString(input, command));
        default:
            throw new CommandNotFoundException();
        }
        this.tasks.add(task);
        return Response.generate(new String[]{
            "Got it. I've added this task:",
            "  " + task.toString(),
            String.format("Now you have %d tasks in the list.", tasks.size())
        });
    }

    /**
     * Lists all tasks and returns a response.
     *
     * @return A Response object listing all tasks.
     */
    protected Response list() {
        ArrayList<String> output = new ArrayList<>();
        output.add("Here are the tasks in your list:");
        int count = 0;
        for (Task task : this.tasks) {
            output.add(String.format("%d.%s",
                ++count,
                task.toString()
            ));
        }
        return Response.generate(output);
    }

    /**
     * Checks if the given index is within a valid range.
     *
     * @param idx The index to check.
     * @return true if the index is within a valid range; false otherwise.
     */
    private boolean inRange(int idx) {
        return (idx > 0 && this.tasks.size() > --idx);
    }

    /**
     * Marks a task as completed and returns a response.
     *
     * @param idx The index of the task to mark.
     * @return A Response object indicating that the task has been marked as done.
     * @throws DukeException if the task index is out of range.
     */
    protected Response mark(int idx) throws DukeException {
        ArrayList<String> output = new ArrayList<>();
        if (!this.inRange(idx)) {
            throw new OutOfRangeException();
        }
        Task task = this.tasks.get(--idx);
        task.mark();
        output.add("Nice! I've marked this task as done:");
        output.add("  " + task.toString());

        return Response.generate(output);
    }

    /**
     * Unmarks a completed task and returns a response.
     *
     * @param idx The index of the task to unmark.
     * @return A Response object indicating that the task has been marked as not done yet.
     * @throws DukeException if the task index is out of range.
     */
    protected Response unmark(int idx) throws DukeException {
        ArrayList<String> output = new ArrayList<>();
        if (!this.inRange(idx)) {
            throw new OutOfRangeException();
        }
        Task task = this.tasks.get(--idx);
        task.unmark();
        output.add("OK, I've marked this task as not done yet:");
        output.add("  " + task.toString());

        return Response.generate(output);
    }

    /**
     * Deletes a task and returns a response.
     *
     * @param idx The index of the task to delete.
     * @return A Response object indicating that the task has been deleted.
     * @throws DukeException if the task index is out of range.
     */
    protected Response delete(int idx) throws DukeException {
        ArrayList<String> output = new ArrayList<>();
        if (!this.inRange(idx)) {
            throw new OutOfRangeException();
        }
        Task task = this.tasks.get(--idx);
        output.add("Noted. I've removed this task:");
        output.add("  " + task.toString());
        this.tasks.remove(idx);
        output.add(String.format("Now you have %d tasks in the list.", tasks.size()));

        return Response.generate(output);
    }

    protected Response find(String keyword) throws DukeException {
        ArrayList<String> output = new ArrayList<>();
        output.add("Here are the matching tasks in your list:");

        int count = 0;
        for (Task task : this.tasks) {
            if (task.name().contains(keyword)) {
                output.add(String.format("%d.%s", ++count, task.toString()));
            }
        }
        if (count == 0) {
            throw new TaskNotFoundException();
        }
        return Response.generate(output);
    }
}
