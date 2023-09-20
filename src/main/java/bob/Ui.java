package bob;

import java.time.LocalDate;
import java.util.ArrayList;

import bob.exceptions.BobEmptyTaskException;
import bob.exceptions.BobException;
import bob.exceptions.BobInvalidEventDateException;
import bob.exceptions.BobInvalidInputException;
import bob.exceptions.BobInvalidTaskException;
import bob.tasks.Deadline;
import bob.tasks.Event;
import bob.tasks.Task;
import bob.tasks.Todo;

/**
 * Represents a parser that deals with interactions with the user.
 */
public class Ui {

    private static Parser parser;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Marks a task as completed according to specified task index.
     *
     * @param list the TaskList containing the tasks.
     * @param markNo index of the task in the list to be marked.
     * @return the string representation of Bob's response.
     * @throws BobException when task does not exist.
     */
    public static String markTask(TaskList list, int markNo) throws BobException {
        if (markNo > 0 && markNo <= list.size()) {
            String response = "Ey! I've marked this task as done:\n" + list.get(markNo - 1).toString();
            list.get(markNo - 1).markAsDone();
            System.out.println(response);
            return response;
        } else {
            throw new BobInvalidTaskException();
        }
    }

    /**
     * Deletes a task from the list according to specified task index.
     *
     * @param list the TaskList containing the tasks.
     * @param deleteNo index of the task in the list to be deleted.
     * @return the string representation of Bob's response.
     * @throws BobException when task does not exist.
     */
    public static String deleteTask(TaskList list, int deleteNo) throws BobException {
        if (deleteNo > 0 && deleteNo <= list.size()) {
            String response = "Okeyy. I've removed this task:\n";
            response += list.get(deleteNo - 1).toString() + "\n";
            list.remove(deleteNo - 1);
            response += "Now you have " + String.valueOf(list.size()) + " tasks in the list.";
            System.out.println(response);
            return response;
        } else {
            throw new BobInvalidTaskException();
        }
    }

    /**
     * Finds task according to the specified keyword and lists them out.
     *
     * @param list the list containing all tasks.
     * @param keyword the keyword used to filter tasks.
     * @return the string representation of Bob's response.
     * @throws BobException when task does not exist.
     */
    public static String findTask(TaskList list, String keyword) throws BobException {
        ArrayList<Task> matches = new ArrayList<Task>();
        String response;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(keyword)) {
                matches.add(list.get(i));
            }
        }

        if (matches.size() == 0) {
            throw new BobInvalidTaskException();
        }

        response = "Here are the matching tasks in your list eyyy:\n";
        for (int i = 1; i <= matches.size(); i++) {
            response += i + ". " + matches.get(i - 1).toString() + "\n";
        }

        System.out.println(response);
        return response;
    }

    /**
     * Reschedules specified Task to specified date(s).
     *
     * @param list the list containing all tasks.
     * @param input the user input containing task index and new rescheduled date(s).
     * @return the string representation of Bob's response.
     * @throws BobException when input is invalid.
     */
    public static String rescheduleTask(TaskList list, String input) throws BobException {
        int rescheduleNo = parser.getRescheduleDigit(input);

        if (list.get(rescheduleNo - 1) instanceof Deadline) {
            //get new due date, change due date, print new task
            String newDueDate = parser.getNewDueDate(input);

            LocalDate d1 = LocalDate.parse(newDueDate);
            ((Deadline) list.get(rescheduleNo - 1)).rescheduleDueDate(d1);

            return printRescheduleMessage(list, rescheduleNo);
        }

        if (list.get(rescheduleNo - 1) instanceof Event) {
            String newStartDate = parser.getNewStartDate(input);
            String newEndDate = parser.getNewEndDate(input);

            LocalDate d1 = LocalDate.parse(newStartDate);
            LocalDate d2 = LocalDate.parse(newEndDate);
            ((Event) list.get(rescheduleNo - 1)).rescheduleEventDate(d1, d2);

            return printRescheduleMessage(list, rescheduleNo);
        }

        throw new BobInvalidInputException();
    }

    private static String printRescheduleMessage(TaskList list, int rescheduleNo) {
        String response = "Okeyy. I've rescheduled this task:\n";
        response += list.get(rescheduleNo - 1).toString();

        System.out.println(response);
        return response;
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param list the TaskList containing the tasks.
     * @param newTask the new Task object that is to be added into the list.
     * @return the string representation of Bob's response.
     */
    public static String addTask(TaskList list, Task newTask) {
        String response;
        list.add(newTask);
        response = "Eyyy. I've added this task:\n";
        response += newTask.toString() + "\n";
        response += "Now you have " + String.valueOf(list.size()) + " tasks in the list.";

        System.out.println(response);
        return response;
    }

    /**
     * Checks what type of task is given in the input, Identifies the task name and dates/times (if applicable),
     * then instantiates the Task of the specified type and adds it to list by calling addTask method.
     *
     * @param list the TaskList containing the tasks.
     * @param task the input string given.
     * @return the string representation of Bob's response.
     * @throws BobException if input is invalid.
     */
    public static String checkAndAddTask(TaskList list, String task) throws BobException {
        if (task.startsWith("todo")) {
            return addTodo(list, task);
        }

        if (task.startsWith("deadline")) {
            return addDeadline(list, task);
        }

        if (task.startsWith("event")) {
            return addEvent(list, task);
        }

        //not a task
        throw new BobInvalidInputException();
    }

    private static String addTodo(TaskList list, String task) throws BobException {
        char[] charArray = task.toCharArray();
        String taskName = "";

        for (int i = 5; i < charArray.length; i++) {
            taskName = taskName + charArray[i];
        }

        if (taskName.isBlank()) {
            throw new BobEmptyTaskException("todo");
        } else {
            Todo thisTask = new Todo(taskName);
            return addTask(list, thisTask);
        }
    }

    private static String addDeadline(TaskList list, String task) throws BobException {
        char[] charArray = task.toCharArray();
        String taskName = "";

        String dueDate = "";
        int byIndex = charArray.length;

        for (int i = 9; i < charArray.length; i++) {
            if (i + 1 < charArray.length && charArray[i + 1] == '/') {
                byIndex = i + 1;
                continue;
            }

            if (i > byIndex + 3) {
                dueDate = dueDate + charArray[i];
            } else if (i < byIndex - 1) {
                taskName = taskName + charArray[i];
            }

        }

        if (taskName.isBlank()) {
            throw new BobEmptyTaskException("deadline");
        } else {
            LocalDate d1 = LocalDate.parse(dueDate);
            Deadline thisTask = new Deadline(taskName, d1);
            return addTask(list, thisTask);
        }
    }

    private static String addEvent(TaskList list, String task) throws BobException {
        char[] charArray = task.toCharArray();
        String taskName = "";
        String startDate = "";
        String endDate = "";
        int fromIndex = charArray.length;
        int toIndex = charArray.length;

        for (int i = 6; i < charArray.length; i++) {
            if (i + 1 < charArray.length && charArray[i + 1] == '/' && fromIndex == charArray.length) {
                fromIndex = i + 1;
                continue;
            } else if (i + 1 < charArray.length && charArray[i + 1] == '/' && fromIndex != charArray.length) {
                toIndex = i + 1;
                continue;
            }

            if (i > fromIndex + 5 && i < toIndex) {
                startDate = startDate + charArray[i];
            } else if (i > toIndex + 3) {
                endDate = endDate + charArray[i];
            } else if (i < fromIndex - 1) {
                taskName = taskName + charArray[i];
            }

        }

        if (taskName.isBlank()) {
            throw new BobEmptyTaskException("event");
        }

        LocalDate d1 = LocalDate.parse(startDate);
        LocalDate d2 = LocalDate.parse(endDate);
        if (d2.isBefore(d1)) {
            throw new BobInvalidEventDateException();
        }
        Event thisTask = new Event(taskName, d1, d2);
        return addTask(list, thisTask);
    }

    /**
     * Prints all the tasks in the list.
     *
     * @param list the TaskList containing the tasks.
     * @return the string representation of Bob's response.
     */
    public static String printTasks(TaskList list) {
        String response;
        response = "Here are the tasks in your list eyy:\n";
        for (int i = 1; i <= list.size(); i++) {
            response += i + ". " + list.get(i - 1).toString() + "\n";
        }

        System.out.println(response);
        return response;
    }

    /**
     * Shows the loading error.
     */
    public void showLoadingError() {
        System.out.println("Error loading file.");
    }

    /**
     * Prints and returns greeting message when user opens application for first time.
     *
     * @return the greeting message.
     */
    public static String printGreeting() {
        String response = "Hello! I'm Bob\n" + "What can I do for you?";
        System.out.println(response);
        return response;
    }

    /**
     * Prints and returns goodbye message when user says bye.
     *
     * @return goodbye message.
     */
    public String printGoodbye() {
        System.out.println("Bye. Hope to see you again soon eyy!");
        return "Bye. Hope to see you again soon eyy!";
    }
}
