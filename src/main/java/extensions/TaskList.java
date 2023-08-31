package extensions;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * TaskList handles task modifications and invalid user inputs for
 * modifying tasks, and is a key component of the Ekud chatbot.
 */
public class TaskList {
    // Same Horizontal Line as Ekud so TaskList can print outputs in a similar UI style
    private final String HORIZONTAL_LINE = "-~-~-~-~-~-~-~-~--~-~-~-~-~-~-~-~-";
    // Actual list storing the tasks
    private List<Task> tasks;
    // Relative path to file with saved tasks.
    private static final String PATH = "src/main/data/savedTasks.txt";
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
     * Prints a confirmation message for the user after adding a task.
     * @param task
     */
    private void showTaskAdded(Task task) {
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
        ToDo newToDo = new ToDo(description);
        this.tasks.add(newToDo);
        this.showTaskAdded(newToDo);
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
            Deadline newDeadline = new Deadline(description, day);
            this.tasks.add(newDeadline);
            this.showTaskAdded(newDeadline);
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
            Event newEvent = new Event(description, from, to);
            this.tasks.add(newEvent);
            this.showTaskAdded(newEvent);
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

    public void loadData() throws EkudIOException {
        File savedTasks = new File(PATH);
        try {
            if (!savedTasks.exists()) {
                System.out.println("Creating task file...");
                savedTasks.createNewFile();
                System.out.println("Task file created successfully");
                return;
            }
            System.out.println("Loading up saved tasks...");
            Scanner scanner = new Scanner(savedTasks);
            while (scanner.hasNext()) {
                // Saved tasks format eg:
                // T | 0 | task1
                // D | 1 | task2 | 1st Sep
                // E | 0 | task 3 | 1st Sep 2pm | 3rd Sep 2pm
                String[] taskDetails = scanner.nextLine().split(" \\| ");
                String taskType = taskDetails[0];
                boolean isDone = taskDetails[1].equals("X");
                if (taskType.equals("T")) {
                    this.tasks.add(new ToDo(taskDetails[2]));
                } else if (taskType.equals("D")) {
                    this.tasks.add(new Deadline(taskDetails[2], taskDetails[3]));
                } else if (taskType.equals("E")) {
                    this.tasks.add(new Event(taskDetails[2], taskDetails[3], taskDetails[4]));
                }
                if (isDone) this.tasks.get(tasks.size() - 1).markAsDone();
            }
            if (tasks.size() == 0) {
                System.out.println("No saved tasks found");
            } else {
                System.out.println("Saved tasks loaded successfully");
            }
        } catch (IOException e) {
            throw new EkudIOException("Error with creating task file: " + e);
        } catch (IndexOutOfBoundsException e) {
            throw new EkudIOException("Error with parsing saved tasks file");
        }
    }
    public void saveData() throws EkudIOException {
        try {
            FileWriter fw = new FileWriter(PATH);
            int len = tasks.size();
            for (int i = 0; i < len; i++) {
                fw.write(tasks.get(i).getSaveFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new EkudIOException("Error with saving tasks");
        }
    }
}
