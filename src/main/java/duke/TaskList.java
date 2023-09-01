package duke;
import dukeUiElements.Ui;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.ListIterator;

public class TaskList {
    public static ArrayList<Task> storeTask = new ArrayList<>(1);   //stores all the tasks. refer to child classes of task

    public static void userListChoice() {
        ListIterator<Task> ls = storeTask.listIterator();
        Ui.printList(ls);
    }
    public static void userMarkUnmark(String taskNumber, String userMarkerChoice) {
        Task taskItem = storeTask.get(Integer.parseInt(taskNumber) - 1);
        System.out.println(Ui.indent + taskItem.changeStatus(userMarkerChoice));
    }
    public static void addToDo(String userDescription) {
        storeTask.add(new ToDo(userDescription));
        Ui.printNumberOfEntries();
    }
    public static void addDeadline(String userDescription, String deadlineBy) {
        try {
            storeTask.add(new Deadline(userDescription, deadlineBy));
            Ui.printNumberOfEntries();
        } catch (DateTimeParseException e) {
            Ui.invalidDateTimeEntry();
        }
    }
    public static void addEvent(String userDescription, String from, String to) {
        try {
            storeTask.add(new Event(userDescription, from, to));
            Ui.printNumberOfEntries();
        } catch (DateTimeParseException e) {
            Ui.invalidDateTimeEntry();
        }
    }
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
