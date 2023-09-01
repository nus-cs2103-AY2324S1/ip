package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides methods to add, delete, and manipulate tasks within the list.
 */
public class DukeList {
    ArrayList<Task> arr;

    /**
     * Constructs a DukeList object with the given initial list of tasks.
     *
     * @param dukelist The initial list of tasks.
     */
    public DukeList(ArrayList<Task> dukelist) {
        this.arr = dukelist;
    }

    /**
     * Constructs a DukeList object with an empty initial list of tasks.
     */
    public DukeList() {
        this.arr = new ArrayList<>(100);
    }

    /**
     * Retrieves the list of tasks stored in the DukeList.
     *
     * @return The list of tasks stored in the DukeList.
     */
    public ArrayList<Task> getList() {
        return this.arr;
    }

    /**
     * Displays a message indicating the addition of a new task to the list.
     *
     * @param task The task that was added.
     */
    public void showList(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println("Now you have " + arr.size() + " tasks in the list");
    }

    /**
     * Deletes a task from the list by its index.
     *
     * @param number The index of the task to be deleted.
     */
    public void deleteTask(int number) {
        Task chosenTask = arr.get(number);
        System.out.println("Noted. I've removed this task:");
        System.out.println("\t" + chosenTask.toString());

        arr.remove(number);
        System.out.println("Now you have " + arr.size() + " tasks in the list");
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param input The description of the Todo task.
     */
    public void addTodo(String input) {
        Todo todo = new Todo(input);
        arr.add(todo);
        showList(todo);
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param input The description of the Deadline task.
     * @param by    The deadline for the task.
     */
    public void addDeadline(String input, LocalDateTime by) {
        Deadline deadline = new Deadline(input, by);
        arr.add(deadline);
        showList(deadline);
    }

    /**
     * Adds an Event task to the list.
     *
     * @param input The description of the Event task.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     */
    public void addEvent(String input, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(input, start, end);
        arr.add(event);
        showList(event);
    }

    /**
     * Prints the list of tasks stored in the DukeList.
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
     * Marks a task as done by its index.
     *
     * @param number The index of the task to be marked as done.
     */
    public void setDone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + chosenTask.toString());
    }

    /**
     * Marks a task as not done by its index.
     *
     * @param number The index of the task to be marked as not done.
     */
    public void setUndone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + chosenTask.toString());
    }
}
