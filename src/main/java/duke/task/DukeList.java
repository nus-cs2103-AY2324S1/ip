package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * DukeList represents a list of tasks in a task management application.
 * It provides methods to add, delete, display, and manipulate tasks.
 */
public class DukeList {

    /** The list of tasks stored in this DukeList. */
    ArrayList<Task> arr;

    /**
     * Constructs a DukeList with the given ArrayList of tasks.
     *
     * @param dukelist The ArrayList of tasks to initialize the DukeList with.
     */
    public DukeList(ArrayList<Task> dukelist) {
        this.arr = dukelist;
    }

    /**
     * Constructs an empty DukeList with an initial capacity of 100.
     */
    public DukeList() {
        this.arr = new ArrayList<>(100);
    }

    /**
     * Retrieves the ArrayList of tasks stored in this DukeList.
     *
     * @return The ArrayList of tasks.
     */
    public ArrayList<Task> getList() {
        return this.arr;
    }

    /**
     * Displays a newly added task and the total number of tasks in the list.
     *
     * @param task The task that was added.
     */
    public void showList(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + arr.size() +  " tasks in the list");
    }

    /**
     * Deletes a task from the list and displays the removed task along with the updated task count.
     *
     * @param number The index of the task to be deleted.
     */
    public void deleteTask(int number) {
        Task chosenTask = arr.get(number);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + chosenTask.toString());

        arr.remove(number);
        System.out.println("Now you have " + arr.size() +  " tasks in the list");
    }

    /**
     * Adds a new Todo task to the list and displays it along with the updated task count.
     *
     * @param input The description of the Todo task.
     */
    public void addTodo(String input) {
        Todo todo = new Todo(input);
        arr.add(todo);
        showList(todo);
    }

    /**
     * Adds a new Deadline task to the list and displays it along with the updated task count.
     *
     * @param input The description of the Deadline task.
     * @param by    The deadline date and time of the task.
     */
    public void addDeadline(String input, LocalDateTime by) {
        Deadline deadline = new Deadline(input, by);
        arr.add(deadline);
        showList(deadline);
    }

    /**
     * Adds a new Event task to the list and displays it along with the updated task count.
     *
     * @param input The description of the Event task.
     * @param start The start date and time of the event.
     * @param end   The end date and time of the event.
     */
    public void addEvent(String input, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(input, start, end);
        arr.add(event);
        showList(event);
    }

    /**
     * Displays all tasks in the list along with their indexes.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        int arrSize = arr.size();
        for (int i = 0; i < arrSize; i++) {
            int num = i + 1;
            Task chosenTask = arr.get(i);
            System.out.println(num + ". " + chosenTask.toString());
        }
    }

    /**
     * Marks a task as done and displays the updated task status.
     *
     * @param number The index of the task to mark as done.
     */
    public void setDone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + chosenTask.toString());
    }

    /**
     * Marks a task as undone (not done yet) and displays the updated task status.
     *
     * @param number The index of the task to mark as undone.
     */
    public void setUndone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + chosenTask.toString());
    }
}
