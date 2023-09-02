package duke.utilities;

import java.util.ArrayList;

import duke.exceptions.InvalidTaskNumberException;

/**
 * Contains the task list and its operations
 */
public class TaskList {

    /** Variable to store the tasks */
    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Creates a TaskList with a predefined list
     * 
     * @param list List to be stored inside the local list variable
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Creates a TaskList with an empty list
     */
    public TaskList() {
        this.list = new ArrayList<>();
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
        this.list.add(task);
        System.out.println("You have added a task:");
        System.out.println("\t[T][ ] " + taskName);
        System.out.println("There are now " + list.size() + " tasks in the list");
    }
    
    /**
     * Adds a deadline task into the list
     *
     * @param taskName Name of task
     * @param deadline Deadline date of task
     */
    public void addDeadline(String taskName, String deadline) {
        Deadline task = new Deadline(taskName, deadline);
        this.list.add(task);
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
    public void addEvent(String taskName, String start, String end) {
        Event task = new Event(taskName, start, end);
        this.list.add(task);
        System.out.println("You have added a task:");
        System.out.println("\t[E][ ] " + taskName + " (from: " + start + " to: " + end + ")");
        System.out.println("There are now " + list.size() + " tasks in the list");
    }

    /**
     * Deletes a task from the list
     * 
     * @param task Task to be deleted
     */
    public void deleteTask(Task task) {
        System.out.println("You have deleted a task:");
        System.out.println("\t" + task.convertToString());
        this.list.remove(task);
        System.out.println("There are now " + this.getSize() + " tasks in the list");
    }

    /**
     * Overwrites the current list with the list stored in hard drive
     * 
     * @param storage Storage object that points to the stored file
     */
    public void overwriteTasksData(Storage storage) {
        storage.overwriteTasksData(this.list);
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public int getSize() {
        return this.list.size();
    }
}
