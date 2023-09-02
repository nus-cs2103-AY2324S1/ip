package duke.utilities;

import java.util.ArrayList;

import duke.exceptions.InvalidTaskNumberException;

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

    /**
     * Adds a todo task into the list
     *
     * @param taskName Name of task
     */
    public void addToDo(String taskName) {
        ToDo task = new ToDo(taskName);
        this.tasks.add(task);
        System.out.println("You have added a task:");
        System.out.println("\t[T][ ] " + taskName);
        System.out.println("There are now " + tasks.size() + " tasks in the list");
    }
    
    /**
     * Adds a deadline task into the list
     *
     * @param taskName Name of task
     * @param deadline Deadline date of task
     */
    public void addDeadline(String taskName, String deadline) {
        Deadline task = new Deadline(taskName, deadline);
        this.tasks.add(task);
        System.out.println("You have added a task:");
        System.out.println("\t[D][ ] " + taskName + " (by: " + deadline + ")");
        System.out.println("There are now " + this.getSize() + " tasks in the list");
    }

    /**
     * Adds an event task into the list
     *
     * @param taskName Name of task
     * @param start Start date of task
     * @param end End date of task
     */
    public void addEvent(String taskName, String startDate, String endDate) {
        Event task = new Event(taskName, startDate, endDate);
        this.tasks.add(task);
        System.out.println("You have added a task:");
        System.out.println("\t[E][ ] " + taskName + " (from: " + startDate + " to: " + endDate + ")");
        System.out.println("There are now " + tasks.size() + " tasks in the list");
    }

    /**
     * Deletes a task from the list
     * 
     * @param task Task to be deleted
     */
    public void deleteTask(Task task) {
        System.out.println("You have deleted a task:");
        System.out.println("\t" + task.convertToString());
        this.tasks.remove(task);
        System.out.println("There are now " + this.getSize() + " tasks in the list");
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
