package echobot.utilities;

import java.util.ArrayList;

import echobot.exceptions.InvalidTaskNumberException;

/**
 * Contains the task list and its operations
 */
public class TaskList {

    /** Variable to store the tasks */
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates a TaskList with a predefined list
     *
     * @param tasks List to be stored inside the local list variable
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a TaskList with an empty list
     */
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
     * @return String output of task manipulated
     * @throws InvalidTaskNumberException If task number is out of bounds
     */
    public String manipulateTasks(String fullInput, String command, int beginIndex) throws InvalidTaskNumberException {
        int taskNum = Integer.parseInt(fullInput.substring(beginIndex));
        if (taskNum > this.getSize() || taskNum < 1) {
            throw new InvalidTaskNumberException("The task number you specify does not exist");
        }
        String output = "";
        switch (command) {
        case "mark":
            output = getTask(taskNum - 1).markAsDone();
            break;
        case "unmark":
            output = getTask(taskNum - 1).markAsNotDone();
            break;
        case "delete":
            output = deleteTask(this.getTask(taskNum - 1));
            break;
        default:
            break;
        }
        return output;
    }

    /**
     * Adds a todo task into the list
     *
     * @param taskName Name of task
     * @return String output of todo task added
     */
    public String addToDo(String taskName) {
        ToDo task = new ToDo(taskName);
        this.tasks.add(task);
        String output = "";
        output += "You have added a task:\n";
        output += "\t[T][  ] " + taskName + "\n";
        output += "There are now " + tasks.size() + " tasks in the list";
        return output;
    }

    /**
     * Adds a deadline task into the list
     *
     * @param taskName Name of task
     * @param deadline Deadline date of task
     * @return String output of deadline task added
     */
    public String addDeadline(String taskName, String deadline) {
        Deadline task = new Deadline(taskName, deadline);
        this.tasks.add(task);
        String output = "";
        output += "You have added a task:\n";
        output += "\t[D][  ] " + taskName + "\n\t(by: " + deadline + ")\n";
        output += "There are now " + this.getSize() + " tasks in the list";
        return output;
    }

    /**
     * Adds an event task into the list
     *
     * @param taskName Name of task
     * @param startDate Start date of task
     * @param endDate End date of task
     * @return String output of event task added
     */
    public String addEvent(String taskName, String startDate, String endDate) {
        Event task = new Event(taskName, startDate, endDate);
        this.tasks.add(task);
        String output = "";
        output += "You have added a task:\n";
        output += "\t[E][  ] " + taskName + "\n\t(from: " + startDate + " to: " + endDate + ")\n";
        output += "There are now " + tasks.size() + " tasks in the list";
        return output;
    }

    /**
     * Deletes a task from the list
     *
     * @param task Task to be deleted
     * @return String output after deleting
     */
    public String deleteTask(Task task) {
        String output = "";
        output += "You have deleted a task:\n";
        output += "\t" + task.convertToString() + "\n";
        this.tasks.remove(task);
        output += "There are now " + this.getSize() + " tasks in the list";
        return output;
    }

    /**
     * Copies and filters the task list to only display tasks with the keyword mentioned
     *
     * @param keyword The keyword that will be compared to the task names
     * @return The filtered list
     */
    public ArrayList<Task> filterTaskName(String keyword) {
        ArrayList<Task> tasksCopied = new ArrayList<>(tasks);
        tasksCopied.removeIf(task -> !task.getName().toLowerCase().contains(keyword.toLowerCase()));
        return tasksCopied;
    }

    /**
     * Overwrites the current list with the list stored in hard drive
     *
     * @param storage Storage object that points to the stored file
     */
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
