package adam;

import java.util.ArrayList;

import adam.tasks.Deadline;
import adam.tasks.Event;
import adam.tasks.Task;
import adam.exception.OutOfBoundException;
import adam.tasks.ToDo;

/**
 * This class holds all the methods that is realted to the task list such as adding and deleting tasks.
 */
public class TaskList {
    private Ui ui = new Ui();
    private boolean isActive = true;
    private ArrayList<Task> tasks;

    /**
     * Initializes the array needed to run this program from the hard disk.
     *
     * @param list ArrayList obtained from reading the file from the hard disk.
     */
    public TaskList(ArrayList<Task> list) {
        tasks = list;
    }

    /**
     * Deletes the Task object from the ArrayList according to its index.
     *
     * @param num Number thatinidcates the index inside the array.
     */
    public void deleteTask(int num) {
        Task curr = tasks.get(num-1);
        tasks.remove(num-1);
        ui.delete(curr, tasks.size());
    }

    /**
     * Adds a Todo Object to the ArrayList.
     *
     * @param text Text inside the todo.
     */
    public void addTodo(String text) {
        ToDo curr = new ToDo(text);
        tasks.add(curr);
        ui.addTodo(curr, tasks.size());
    }

    /**
     * Adds a Deadline Object to the ArrayList.
     *
     * @param text Text inside the deadline.
     * @param by By what time the deadline is due.
     */
    public void addDeadline(String text, String by) {
        Deadline curr = new Deadline(text, by);
        tasks.add(curr);
        ui.addDeadline(curr, tasks.size());
    }

    /**
     * Adds an Event Object to the ArrayList.
     *
     * @param text Text inside the event.
     * @param from From what time does the event start.
     * @param to To what time does the event ends.
     */
    public void addEvent(String text, String from, String to) {
        Event curr = new Event(text, from, to);
        tasks.add(curr);
        ui.addEvent(curr, tasks.size());
    }

    /**
     * Prints all the existing tasks inside the list.
     */
    public void list() {
        ui.list();
        int count = 1;
        for (Task item: tasks) {
            System.out.println(count + ". " + item.toString());
            count++;
        }
    }

    /**
     * Marks the intended Tasks as complete according to their index.
     *
     * @param number Number of index that is going to be marked.
     */
    public void markAsDone(int number) {
        Task curr = tasks.get(number - 1);
        ui.mark();
        curr.markAsDone();
    }

    /**
     * Unmarks the intended Tasks as uncomplete according to their index.
     *
     * @param number Number of index that is going to be unmarked.
     */
    public void unmarkAsDone(int number) {
        Task curr = tasks.get(number - 1);
        ui.unmark();
        curr.unmarkAsDone();
    }

    /**
     * Returns a boolean that indicates whether the program is still runnning or not.
     *
     * @return Boolean.
     */
    public boolean isRunning() {
        return isActive;
    }

    /**
     * Ends the program.
     */
    public void bye() {
        isActive = false;
        ui.bye();
    }

    /**
     * Saves the array to the storage.
     *
     * @param storage Storage Object.
     */
    public void save (Storage storage) {
        storage.write(tasks);
    }

    /**
     * Returns the size of the array.
     *
     * @return Size of the array.
     */
    public int getSize() {
        return tasks.size();
    }
}
