package extensions;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    // Same horizontalLine from Ekud so TaskList can print outputs in a similar UI style
    private final String horizontalLine = "-~-~-~-~-~-~-~-~--~-~-~-~-~-~-~-~-";
    // Actual list storing the tasks
    private final List<Task> tasks;
    // Constructor for TaskList
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Similar to echo() method from Ekud for TaskList to be able to print to console
     * in a similar UI style.
     * @param message Output text in between 2 horizontal lines.
     */
    public void echo(String message) {
        System.out.println(String.format("%s\n%s\n%s",
                horizontalLine,
                message,
                horizontalLine));
    }
    /**
     * Prints this TaskList to the console.
     */
    public void showTasks() {
        System.out.println(horizontalLine);
        System.out.println("Here is your to-do list:");
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
        }
        System.out.println(horizontalLine);
    }
    /**
     * Marks a specific task as done.
     * @param index Index number of task to be marked.
     */
    public void markTaskAsDone(int index) throws EkudIllegalArgException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            String message = "The following task is marked done, sheeesh:\n" + task.toString();
            this.echo(message);
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Task index number is out of bounds :/");
        }
    }
    /**
     * Marks a specific task as not done.
     * @param index Index number of task to be marked.
     */
    public void markTaskAsNotDone(int index) throws EkudIllegalArgException {
        try {
            Task task = tasks.get(index);
            task.markAsNotDone();
            String message = "The following task is marked as not done yet:\n" + task.toString();
            this.echo(message);
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
     * @param description Description of to-do task.
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
     * @param deadlineInfo Args needed for deadline task.
     * @throws EkudIllegalArgException Illegal arg(s) for deadline task.
     */
    public void addDeadline(String deadlineInfo) throws EkudIllegalArgException {
        try {
            String[] userArgs = deadlineInfo.split(" /by ");
            String description = userArgs[0];
            String day = userArgs[1];
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
     * @param eventInfo Args needed for event task.
     * @throws EkudIllegalArgException Illegal arg(s) for event task.
     */
    public void addEvent(String eventInfo) throws EkudIllegalArgException {
        try {
            String[] userArgs = eventInfo.split(" /from ");
            String[] timings = userArgs[1].split(" /to ");
            String description = userArgs[0];
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
    public void deleteTask(int index) throws EkudIllegalArgException {
        if (tasks.isEmpty()) {
            throw new EkudIllegalArgException("You cannot delete from an empty task list :/");
        }
        try {
            Task task = tasks.get(index);
            this.tasks.remove(index);
            this.echo(String.format(
                    "Alright, this task has been removed:\n%s\nNow you have %d task(s) in the list.",
                    task.toString(),
                    tasks.size()));
        } catch(IndexOutOfBoundsException e) {
            throw new EkudIllegalArgException("Task index number is out of bounds :/");
        }
    }
}
