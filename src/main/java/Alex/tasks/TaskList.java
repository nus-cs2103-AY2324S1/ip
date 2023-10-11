package Alex.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Alex.AlexException;


/**
 * A class represents a "temporary storage" for the task(s) key in by the user every time the user run the chat bot
 * Alex. The task(s) stored in TaskList will eventually be stored into Alex.txt by the method from UserInputStorage
 * class after termination of Alex chatbot execution. Similarly, the user data stored in the file Alex.txt will
 * be restored to TaskList everytimne the user initialize/run the Alex chatbot.
 */
public class TaskList {
    private static ArrayList<Task> userinputs = new ArrayList<>(100);

    private static int numberOfElements = 0;

    /**
     * A static method that is used to get the current number of task(s) in TaskList.
     * @return the current number of task(s) in TaskList.
     */
    public static int getNumberOfElements() {
        return numberOfElements;
    }


    /**
     * A static method that is used to remove all the task(s) stored in TaskList.
     */
    public static void clearAll() {
        userinputs = new ArrayList<>(100);
        numberOfElements = 0;
    }

    /**
     * A static method that is used to store a task specified by the user.
     * @param task the task object representing the task specified by the user.
     */
    public static String store(Task task) {
        userinputs.add(task);
        numberOfElements++;
        String output = "Got it. I've added this task:\n"
                + "  "
                + task.toString() + "\n"
                + "Now you have " + numberOfElements + " tasks in the list.";

        return output;
    }

    /**
     * A static method that is used to store a new task object into TaskList with another parameter (printToUser)
     * of type boolean to specify whether to print the message to the user after successfully storing the task.
     *
     * @param task the task object representing the task specified by the user.
     * @param printToUser a boolean parameter used to specify wheter to print the message to the user after storing.
     */
    public static String store(Task task, boolean printToUser) {
        String output = null;
        if (printToUser) {
            output = store(task);
        } else {
            userinputs.add(task);
            numberOfElements++;
        }
        return output;
    }

    /**
     * A static that is used to get the task object in the TaskList when a task index is given. This method
     * expects the index starting from 1.
     *
     * @param index the task index in the TaskList, index starting from 1.
     * @return the task instance stored in the TaskList
     * @throws AlexException if an error occurs when passing in invalid task index.
     */
    public static Task getTaskByIndex(int index) throws AlexException {
        if (index > numberOfElements) {
            String message = "OOPS!!! There is/are only " + numberOfElements + " task(s) stored";
            throw new AlexException(message);
        } else if (index < 0) {
            String message = "OOPS!!! Task number cannot be negative, task number starts from 0";
            throw new AlexException(message);
        }
        return userinputs.get(index - 1);
    }

    /**
     * A static method that is used to print all the task(s) stored in TaskList.
     */
    public static String getAllContent() {
        String tobePrinted = "";
        for (int i = 0; i < numberOfElements; i++) {
            tobePrinted = tobePrinted + (i + 1) + ". " + userinputs.get(i).toString() + "\n";
        }

        return tobePrinted;
    }

    /**
     * A static method that is used to remove a specific task in TaskList when the task index is given.
     *
     * @param index the index of the task to be removed.
     * @throws AlexException if an error occurs when an invalid task index is passed into this method.
     */
    public static String delete(int index) {
        if (index > numberOfElements) {
            String message = "OOPS!!! There is/are only " + numberOfElements + " task(s) stored";
            return message;
        } else if (index < 0) {
            String message = "OOPS!!! Task number cannot be negative, task number starts from 0";
            return message;
        }
        Task tobeRemoved = userinputs.remove(index - 1);
        numberOfElements--;
        String output = "Noted. I've removed this task:\n"
                + "  " + tobeRemoved.toString() + "\n"
                + "Now you have " + getNumberOfElements() + " tasks in the list.\n";

        return output;

    }

    /**
     * A static method that is used to print all the task(s) of a specific date when a string representing
     * that date is given.
     *
     * @param date a string representing the date in which the user wants to view all the task(s) in that day.
     * @throws DateTimeParseException if an error occurs when parsing the string representing a date.
     */
    public static String getTaskForDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        String tobePrinted = "";
        int count = 1;

        for (int i = 0; i < numberOfElements; i++) {
            Task task = userinputs.get(i);
            if (task instanceof ToDos) {
                continue;
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDueDate().equals(parsedDate)) {
                    tobePrinted = tobePrinted + count + ". " + deadline + "\n";
                    count++;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFromDate().equals(parsedDate) || event.getToDate().equals(parsedDate)) {
                    tobePrinted = tobePrinted + count + ". " + event + "\n";
                    count++;
                }
            }
        }

        String output = "There is/are a total of " + (count - 1) + " task(s) on the give date " + date + ":\n"
                        + tobePrinted;
        return output;
    }

    public static String getTaskForMatchWord(String word) {
        String tobePrinted = "Here are the matching tasks in your list:\n";
        int count = 1;
        String regex = "\\b" + word + "\\b";
        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < numberOfElements; i++) {
            Task task = userinputs.get(i);
            Matcher matcher = pattern.matcher(task.toString());
            if (matcher.find()) {
                tobePrinted += count + ". " + task + "\n";
                count++;
            }
        }
        return tobePrinted;
    }

}
