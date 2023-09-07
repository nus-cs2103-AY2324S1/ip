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
    private static ArrayList<Task> storeTask = new ArrayList<>(1);   //stores all the tasks
    /**
     * Displays the entire list of tasks to user.
     */
    public static String userListChoice() {
        ListIterator<Task> ls = storeTask.listIterator();
        return Ui.printList(ls);
    }

    /**
     * Marks or unmarks the task.
     *
     * @param taskNumber The task number to be marked/unmarked.
     * @param userMarkerChoice User choice mark/unmark.
     */
    public static String userMarkUnmark(String taskNumber, String userMarkerChoice) {
        Task taskItem = storeTask.get(Integer.parseInt(taskNumber) - 1);
        return Ui.indent + taskItem.changeStatus(userMarkerChoice);
    }

    /**
     * Adds task with given description to ArrayList.
     *
     * @param userDescription Description attached to task.
     */
    public static String addToDo(String userDescription) {
        storeTask.add(new ToDo(userDescription));
        return Ui.printNumberOfEntries();
    }

    /**
     * Adds a new Deadline task to ArrayList.
     *
     * @param userDescription Description attached to Deadline.
     * @param deadlineBy Deadline given in yyyy-mm-dd HH:mm format.
     */
    public static String addDeadline(String userDescription, String deadlineBy) {
        try {
            storeTask.add(new Deadline(userDescription, deadlineBy));
            return Ui.printNumberOfEntries();
        } catch (DateTimeParseException e) {
            return Ui.invalidDateTimeEntry();
        }
    }

    /**
     * Adds a new Deadline task to ArrayList.
     *
     * @param userDescription Description attached to Event.
     * @param from From Date & Time given in yyyy-mm-dd HH:mm format.
     * @param to To Date & Time given in yyyy-mm-dd HH:mm format.
     */
    public static String addEvent(String userDescription, String from, String to) {
        try {
            storeTask.add(new Event(userDescription, from, to));
            return Ui.printNumberOfEntries();
        } catch (DateTimeParseException e) {
            return Ui.invalidDateTimeEntry();
        }
    }

    /**
     * Deletes task from ArrayList.
     *
     * @param delUserChoice The task number to be deleted (based on number on list).
     * @throws DukeException  If TaskList is empty or invalid selection by user.
     */
    public static String deleteTask(int delUserChoice) throws DukeException{
        if ((delUserChoice - 1) < 0) {                                          //if number entered smaller than 1, array will go negative index.
            throw new DukeException("Invalid Task entered. Please try again...");
        } else if (storeTask.isEmpty()) {
            throw new DukeException("Task Scheduler is empty... Please try again!");
        } else {
            Task itemRemoved = storeTask.remove(delUserChoice - 1);
            return Ui.deleteTaskPrint(itemRemoved);
        }
    }
    public static String taskToBeFound(String findThis) {
        ListIterator<Task> ls = storeTask.listIterator();
        ArrayList<Task> filteredList = new ArrayList<>();
        while (ls.hasNext()) {
            Task current = ls.next();
            String currentDescription = current.getDescription();
            if (currentDescription.contains(findThis)) {
                filteredList.add(current);
            }
        }
        ListIterator<Task> iterFilteredList = filteredList.listIterator();
        if (filteredList.size() == 0) {
            return Ui.emptyList();
        } else {
            return Ui.findTaskPrint(iterFilteredList);
        }
    }

    public static ArrayList<Task> getStoreTask() {
        return storeTask;
    }
    public static int getTaskSize() {
        return storeTask.size();
    }
}
