package extensions;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList handles task modifications and invalid user inputs for
 * modifying tasks, and is a key component of the Ekud chatbot.
 */
public class TaskList {
    // Same Horizontal Line as Ekud so TaskList can print outputs in a similar UI style
    private final String HORIZONTAL_LINE = "-~-~-~-~-~-~-~-~--~-~-~-~-~-~-~-~-";
    // Actual list storing the tasks
    private List<Task> tasks;
    // Constructor for TaskList
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Allows TaskList to print output in a similar UI style as Ekud with the
     * same echo() method.
     * @param message Output text in between 2 horizontal lines.
     */
    public void echo(String message) {
        System.out.println(String.format("%s\n%s\n%s",
                HORIZONTAL_LINE,
                message,
                HORIZONTAL_LINE));
    }
    /**
     * Prints this TaskList to the console.
     */
    public void showTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here is your to-do list:");
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
        }
        System.out.println(HORIZONTAL_LINE);
    }
    /**
     * Marks a specific task as done.
     * @param userArg Index number of task supplied by user.
     * @throws EkudIllegalArgException Illegal arg for index number.
     */
    public void markTaskAsDone(String userArg) throws EkudIllegalArgException {
        try {
            int index = Integer.valueOf(userArg) - 1;
            Task task = tasks.get(index);
            task.markAsDone();
            this.echo("The following task is marked done, sheeesh:\n"
                        + task.toString());
        } catch(NumberFormatException e) {
            throw new EkudIllegalArgException("Please input a valid index number :o");
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Task index number is out of bounds :/");
        }
    }
    /**
     * Marks a specific task as not done.
     * @param userArg Index number of task supplied by user.
     * @throws EkudIllegalArgException Illegal arg for index number.
     */
    public void markTaskAsNotDone(String userArg) throws EkudIllegalArgException {
        try {
            int index = Integer.valueOf(userArg) - 1;
            Task task = tasks.get(index);
            task.markAsNotDone();
            this.echo("The following task is marked as not done yet:\n"
                        + task.toString());
        } catch(NumberFormatException e) {
            throw new EkudIllegalArgException("Please input a valid index number :o");
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Task index number is out of bounds :/");
        }
    }
    /**
     * Adds a task to this TaskList before printing a confirmation message,
     * to be used internally.
     * @param task Task object to be added.
     */
    private void addTask(Task task) {
        this.tasks.add(task);
        this.echo(String.format(
                "Got it! I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task.toString(),
                tasks.size()));
    }
    /**
     * Adds a to-do task to this TaskList.
     * @param description Description of to-do task by user.
     * @throws EkudIllegalArgException Illegal arg for to-do task.
     */
    public void addToDo(String description) throws EkudIllegalArgException {
        if (description.isBlank()) { // isBlank() checks if string is all whitespace
            throw new EkudIllegalArgException("Todo task shouldn't be empty :(");
        }
        addTask(new ToDo(description));
    }
    /**
     * Adds a deadline task to this TaskList.
     * @param userArgs Args supplied by user for adding deadline task.
     * @throws EkudIllegalArgException Illegal arg(s) for deadline task.
     */
    public void addDeadline(String userArgs) throws EkudIllegalArgException {
        try {
            String[] deadlineArgs = userArgs.split(" /by ");
            String description = deadlineArgs[0];
            String day = deadlineArgs[1];
            if (description.isBlank() || day.isBlank()) {
                throw new EkudIllegalArgException("Description/day shouldn't be empty :(");
            }
            addTask(new Deadline(description, day));
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Deadline formatted wrongly, " +
                    "ensure 'deadline <description> /by <day>' is followed");
        }
    }
    /**
     * Adds an event task to this TaskList.
     * @param userArgs Args supplied by user for adding event task.
     * @throws EkudIllegalArgException Illegal arg(s) for event task.
     */
    public void addEvent(String userArgs) throws EkudIllegalArgException {
        try {
            String[] eventArgs = userArgs.split(" /from ");
            String[] timings = eventArgs[1].split(" /to ");
            String description = eventArgs[0];
            String from = timings[0];
            String to = timings[1];
            if (description.isBlank() || from.isBlank() || to.isBlank()) {
                throw new EkudIllegalArgException("Description/start/end shouldn't be empty :(");
            }
            addTask(new Event(description, from, to));
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Event formatted wrongly, " +
                    "ensure 'event <description> /from <start> /to <end>' is followed");
        }
    }
    /**
     * Deletes a task from this TaskList and prints a confirmation message.
     * @param userArg Index number of task to be deleted as supplied by user.
     * @throws EkudIllegalArgException Illegal arg for index number.
     */
    public void deleteTask(String userArg) throws EkudIllegalArgException {
        if (tasks.isEmpty()) {
            throw new EkudIllegalArgException("You cannot delete from an empty task list :/");
        }
        try {
            int index = Integer.valueOf(userArg) - 1;
            Task task = tasks.get(index);
            this.tasks.remove(index);
            this.echo(String.format(
                    "Alright, this task has been removed:\n%s\nNow you have %d task(s) in the list.",
                    task.toString(),
                    tasks.size()));
        } catch(NumberFormatException e) {
            throw new EkudIllegalArgException("Please input a valid index number :o");
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Task index number is out of bounds :/");
        }
    }
}
