import java.util.ArrayList;

/**
 * The task list is used to store the user's tasks.
 */
public class TaskList {

    // The divider is used to separate the user's input from LilBro's output.
    private static final String DIVIDER = "____________________________________________________________";

    // The list is used to store the user's tasks.
    private ArrayList<Task> list;

    /**
     * Creates a new TaskList object.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Adds a new task to the list.
     * 
     * @param type       The type of task to be added.
     * @param taskString Information about the task to be added.
     * @throws DukeInvalidArgumentException If the given taskString is invalid.
     */
    public void addTask(TaskType type, String taskString) throws DukeInvalidArgumentException {
        switch (type) {
        case TODO:
            if (taskString.equals("")) {
                throw new DukeInvalidArgumentException("You didn't specify a task to do. " +
                        "Check that you're doing \"todo {description}\".");
            }
            this.list.add(new ToDo(taskString));
            break;

        case DEADLINE:
            try {
                String[] deadlineParts = taskString.split(" /by ", 2);
                this.list.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeInvalidArgumentException(
                        "Your deadline seems to be formatted wrongly. " +
                                "Check that you're doing: \"deadline {description} /by {date}\".");
            }
            break;

        case EVENT:
            try {
                String[] eventParts = taskString.split(" /from ", 2);
                String description = eventParts[0].trim();
                String[] eventTimeParts = eventParts[1].trim().split(" /to ", 2);
                this.list.add(new Event(description, eventTimeParts[0].trim(), eventTimeParts[1].trim()));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeInvalidArgumentException(
                        "Your event seems to be formatted wrongly. " +
                                "Check that you're doing: \"event {description} /from {start} /to {end}\".");
            }
            break;

        default:
            throw new DukeInvalidArgumentException("I'm gonna be honest, no idea what you're saying.");
        }

        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task:");
        System.out.println(this.list.get(this.list.size() - 1));
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    /**
     * Marks specified task as done.
     * 
     * @param index The index of the task to be marked as done.
     * @throws DukeInvalidArgumentException If the index is invalid.
     */
    public void markTaskDone(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.list.size()) {
            throw new DukeInvalidArgumentException(
                    "You've specified an invalid task number. Check your task list again with the \"list\" command.");
        }

        this.list.get(index - 1).markDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.list.get(index - 1));
        System.out.println(DIVIDER);
    }

    /**
     * Unmarks specified task as not done.
     * 
     * @param index The index of the task to be unmarked as not done.
     * @throws DukeInvalidArgumentException If the index is invalid.
     */
    public void unmarkTaskDone(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.list.size()) {
            throw new DukeInvalidArgumentException(
                    "You've specified an invalid task number. Check your task list again with the \"list\" command.");
        }

        this.list.get(index - 1).unmarkDone();
        System.out.println(DIVIDER);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.list.get(index - 1));
        System.out.println(DIVIDER);
    }

    /**
     * Deletes specified task.
     * 
     * @param index The index of the task to be deleted.
     * @throws DukeInvalidArgumentException If the index is invalid.
     */
    public void deleteTask(int index) throws DukeInvalidArgumentException {
        if (index <= 0 || index > this.list.size()) {
            throw new DukeInvalidArgumentException(
                    "You've specified an invalid task number. Check your task list again with the \"list\" command.");
        }

        System.out.println(DIVIDER);
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.list.get(index - 1));
        System.out.println(DIVIDER);
        this.list.remove(index - 1);
    }

    /**
     * Lists all tasks.
     */
    public void listTasks() {
        System.out.println(DIVIDER);
        if (this.list.isEmpty()) {
            System.out.println("You have no tasks so far.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.list.size(); i++) {
                System.out.println((i + 1) + ". " + this.list.get(i));
            }
        }

        System.out.println(DIVIDER);
    }

    public int size() {
        return this.list.size();
    }
}
