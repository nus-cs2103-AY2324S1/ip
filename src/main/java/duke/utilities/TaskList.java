package duke.utilities;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import duke.exceptions.InvalidTaskNumberException;

/**
 * Contains the task list and its operations
 */
public class TaskList {

    /** Variable to store the tasks */
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Manipulates the data of existing tasks
     * Includes marking, unmarking, and deleting tasks
     * 
     * @param fullInput Full String input by user
     * @param command First word of input that signifies the command to be run
     * @param beginIndex Beginning index of the command description excluding the command itself
     * @throws InvalidTaskNumberException
     */
    public void manipulateTasks(String fullInput, String command, int beginIndex) throws InvalidTaskNumberException {
        int taskNum = Integer.parseInt(fullInput.substring(beginIndex));
        if (taskNum > this.getSize() || taskNum < 1) {
            throw new InvalidTaskNumberException("Task number is out of bounds, please try again");
        }
        switch (command) {
        case "mark":
            this.getTask(taskNum - 1).markAsDone();
            break;
        case "unmark":
            this.getTask(taskNum - 1).markAsNotDone();
            break;
        case "delete":
            this.deleteTask(this.getTask(taskNum - 1));
            break;
        }
    }

    public void addToDo(String taskName) {
        ToDo task = new ToDo(taskName);
        this.tasks.add(task);
        System.out.println("You have added a task:");
        System.out.println("\t[T][ ] " + taskName);
        System.out.println("There are now " + tasks.size() + " tasks in the list");
    }
    
    public void addDeadline(String taskName, String deadline) {
        Deadline task = new Deadline(taskName, deadline);
        this.tasks.add(task);
        System.out.println("You have added a task:");
        System.out.println("\t[D][ ] " + taskName + " (by: "+ deadline + ")");
        System.out.println("There are now " + this.getSize() + " tasks in the list");
    }

    public void addEvent(String taskName, String start, String end) {
        Event task = new Event(taskName, start, end);
        this.tasks.add(task);
        System.out.println("You have added a task:");
        System.out.println("\t[E][ ] " + taskName + " (from: "+ start + " to: " + end + ")");
        System.out.println("There are now " + tasks.size() + " tasks in the list");
    }

    /**
     * Deletes a task from the specified list
     * 
     * @param task Task object to be deleted
     */
    public void deleteTask(Task task) {
        System.out.println("You have deleted a task:");
        System.out.println("\t" + task.convertToString());
        this.tasks.remove(task);
        System.out.println("There are now " + this.getSize() + " tasks in the list");
    }

    public void overwriteTasksData(Storage storage) {
        storage.overwriteTasksData(this.tasks);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }
}
