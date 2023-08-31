package adam;

import adam.tasks.Deadlines;
import adam.tasks.Events;
import adam.tasks.Task;
import adam.exception.OutOfBoundsException;
import adam.tasks.ToDos;
import java.util.ArrayList;

/**
 * This class holds all the methods that is realted to the task list such as adding and deleting tasks.
 */
public class TaskList {
    Ui ui = new Ui();
    boolean running = true;
    private ArrayList<Task> array;

    /**
     * Initializes the array needed to run this program from the hard disk.
     *
     * @param saved ArrayList obtained from reading the file from the hard disk.
     */
    public TaskList(ArrayList<Task> saved) {
        array = saved;
    }

    /**
     * Deletes the Task object from the ArrayList according to its index.
     *
     * @param num Number thatinidcates the index inside the array.
     */
    public void deleteTask(int num) {
        Task curr = array.get(num-1);
        array.remove(num-1);
        ui.delete(curr, array.size());
    }

    /**
     * Adds a Todo Object to the ArrayList.
     *
     * @param text Text inside the todo.
     */
    public void addTodo(String text) {
        ToDos curr = new ToDos(text);
        array.add(curr);
        ui.addTodo(curr, array.size());
    }

    /**
     * Adds a Deadline Object to the ArrayList.
     *
     * @param text Text inside the deadline.
     * @param by By what time the deadline is due.
     */
    public void addDeadline(String text, String by) {
        Deadlines curr = new Deadlines(text, by);
        array.add(curr);
        ui.addDeadline(curr,array.size());
    }

    /**
     * Adds an Event Object to the ArrayList.
     *
     * @param text Text inside the event.
     * @param from From what time does the event start.
     * @param to To what time does the event ends.
     */
    public void addEvent(String text, String from, String to) {
        Events curr = new Events(text, from, to);
        array.add(curr);
        ui.addEvent(curr,array.size());
    }

    /**
     * Prints all the existing tasks inside the list.
     */
    public void list() {
        ui.list();
        int count = 1;
        for (Task item: array) {
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
        if (number > array.size()) {
            throw new OutOfBoundsException();
        }
        Task curr = array.get(number - 1);
        ui.mark();
        curr.markAsDone();
    }

    /**
     * Unmarks the intended Tasks as uncomplete according to their index.
     *
     * @param number Number of index that is going to be unmarked.
     */
    public void unMarkAsDone(int number) {
        if (number > array.size()) {
            throw new OutOfBoundsException();
        }
        Task curr = array.get(number - 1);
        ui.unmark();
        curr.unmarkAsDone();
    }

    /**
     * Returns a boolean that indicates whether the program is still runnning or not.
     *
     * @return Boolean.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Ends the program.
     */
    public void bye() {
        running = false;
        ui.bye();
    }

    /**
     * Saves the array to the storage.
     *
     * @param storage Storage Object.
     */
    public void save (Storage storage) {
        storage.write(array);
    }
}
