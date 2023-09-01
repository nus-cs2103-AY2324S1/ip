package duke;
import dukeUiElements.Ui;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * This class holds the ArrayList to load all the tasks. Contains important Task manipulation methods.
 */
public class TaskList {
    public static ArrayList<Task> storeTask = new ArrayList<>(1);   //stores all the tasks. refer to child classes of task

    /**
     * Displays the entire list of tasks to user.
     */
    public static void userListChoice() {
        ListIterator<Task> ls = storeTask.listIterator();
        Ui.printList(ls);
    }

    /**
     * Marks or unmarks the task.
     *
     * @param taskNumber The task number to be marked/unmarked.
     * @param userMarkerChoice User choice mark/unmark.
     */
    public static void userMarkUnmark(String taskNumber, String userMarkerChoice) {
        Task taskItem = storeTask.get(Integer.parseInt(taskNumber) - 1);
        System.out.println(Ui.indent + taskItem.changeStatus(userMarkerChoice));
    }

    /**
     * Adds task with given description to ArrayList.
     *
     * @param userDescription Description attached to task.
     */
    public static void addToDo(String userDescription) {
        storeTask.add(new ToDo(userDescription));
        Ui.printNumberOfEntries();
    }

    /**
     * Adds a new Deadline task to ArrayList.
     *
     * @param userDescription Description attached to Deadline.
     * @param deadlineBy Deadline given in yyyy-mm-dd HH:mm format.
     */
    public static void addDeadline(String userDescription, String deadlineBy) {
        try {
            storeTask.add(new Deadline(userDescription, deadlineBy));
            Ui.printNumberOfEntries();
        } catch (DateTimeParseException e) {
            Ui.invalidDateTimeEntry();
        }
    }

    /**
     * Adds a new Deadline task to ArrayList.
     *
     * @param userDescription Description attached to Event.
     * @param from From Date & Time given in yyyy-mm-dd HH:mm format.
     * @param to To Date & Time given in yyyy-mm-dd HH:mm format.
     */
    public static void addEvent(String userDescription, String from, String to) {
        try {
            storeTask.add(new Event(userDescription, from, to));
            Ui.printNumberOfEntries();
        } catch (DateTimeParseException e) {
            Ui.invalidDateTimeEntry();
        }
    }

    /**
     * Deletes task from ArrayList.
     *
     * @param delUserChoice The task number to be deleted (based on number on list).
     * @throws DukeException  If TaskList is empty or invalid selection by user.
     */
    public static void deleteTask(int delUserChoice) throws DukeException{
        if ((delUserChoice - 1) < 0) {                                          //if number entered smaller than 1, array will go negative index.
            throw new DukeException("Invalid Task entered. Please try again...");
        } else if (storeTask.isEmpty()) {
            throw new DukeException("Task Scheduler is empty... Please try again!");
        } else {
            Task itemRemoved = storeTask.remove(delUserChoice - 1);
            System.out.println(Ui.indent + "This task was removed..." + "\n" + itemRemoved);
            System.out.println(Ui.indent + "Now you have " + storeTask.size() + " tasks in your task scheduler...");
        }
    }
}
