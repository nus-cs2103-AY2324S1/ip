package rat.storage;

import java.util.ArrayList;

import static rat.print.RatPrinter.*;

/**
 * This class encapsulates the storage of Rat, which is a list of tasks.
 * Includes methods to add, modify, and delete tasks.
 *
 * @author Keagan
 */
public class RatStorage {

    /**
     * The list of tasks managed by RatStorage, represented by an ArrayList.
     */
    private final ArrayList<Task> storage;

    /**
     * Constructor for a RatStorage object.
     * Initialises the storage as an empty ArrayList.
     */
    public RatStorage() {
        storage = new ArrayList<>();
    }

    /**
     * Returns the String representation of RatStorage.
     * The String representation is a numbered list of tasks.
     *
     * @return String representation of RatStorage.
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.storage.size(); i++) {
            str += (i + 1) + ". " + this.storage.get(i).toString() + "\n";
        }
        return str;
    }

    /**
     * Adds a ToDo task to the storage, given its name.
     * Instantiates a new ToDo object and adds it to the storage.
     *
     * @param item Name of the ToDo task.
     */
    public void addToDo(String item) {
        ToDo newToDo = new ToDo(item);
        this.storage.add(newToDo);
        printWithLines("Got it. I've added this Deadline:\n"
                + newToDo
                + "\nNow you have " + this.storage.size() + " tasks in the list.");
    }

    /**
     * Adds a Deadline task to the storage, given its deadline and name.
     * Instantiates a new Deadline object and adds it to the storage.
     *
     * @param deadline Deadline of the Deadline task.
     * @param name     Name of the Deadline task.
     */
    public void addDeadline(String deadline, String name) {
        Deadline newDeadline = new Deadline(deadline, name);
        this.storage.add(newDeadline);
        String msg = "Got it. I've added this Deadline:\n"
                + newDeadline
                + "\nNow you have " + this.storage.size() + " tasks in the list.";
        printWithLines(msg);
    }

    /**
     * Adds an Event task to the storage, given its start time, end time, and name.
     * Instantiates a new Event object and adds it to the storage.
     *
     * @param startTime Start time of the Event task.
     * @param endTime   End time of the Event task.
     * @param name      Name of the Event task.
     */
    public void addEvent(String startTime, String endTime, String name) {
        Event newEvent = new Event(startTime, endTime, name);
        this.storage.add(newEvent);
        String msg = "Got it. I've added this Event:\n"
                + newEvent
                + "\nNow you have " + this.storage.size() + " tasks in the list.";
        printWithLines(msg);
    }

    /**
     * Marks a task as done, given its 1-indexed index in the storage.
     *
     * @param index Index of the task in the storage.
     */
    public void markItemDone(int index) {
        if (index > this.storage.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.storage.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.storage.get(index - 1);
        item.markDone();
        printWithLines("Nice! I've marked this task as done: " + storage.get(index - 1).toString());
    }

    /**
     * Marks a task as not done, given its 1-indexed index in the storage.
     *
     * @param index Index of the task in the storage.
     */
    public void unmarkItemDone(int index) {
        if (index > this.storage.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.storage.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.storage.get(index - 1);
        item.unmarkDone();
        printWithLines("Ok, I've marked this task as not done yet: " + storage.get(index - 1).toString());
    }

    /**
     * Deletes a task from storage, given its 1-indexed index in the storage.
     *
     * @param index Index of the task in the storage.
     */
    public void deleteItem(int index) {
        if (index > this.storage.size() || index < 1) {
            throw new IndexOutOfBoundsException("Task not found");
        } else if (this.storage.get(index - 1) == null) {
            throw new IndexOutOfBoundsException("Task not found");
        }
        Task item = this.storage.get(index - 1);
        this.storage.remove(index - 1);
        printWithLines("Noted. I've removed this task:\n"
                + item.toString()
                + "\nNow you have " + this.storage.size() + " tasks in the list.");
    }

    /**
     * Deletes all tasks from storage.
     */
    public void deleteAll() {
        this.storage.clear();
        printWithLines("Noted. I've removed all tasks.");
    }

    /**
     * Displays all tasks in storage.
     * Formats the storage's String representation with a message and count.
     */
    public void listItems() {
        if (this.storage.isEmpty()) {
            printWithLines("You have no tasks in the list.");
        } else {
            String list = "Here are the tasks in your list:\n"
                    + this + "\n"
                    + "You have " + this.storage.size() + " tasks in the list.";
            printWithLines(list);
        }
    }
}