package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
/**
 * The TaskList class is in charge of the different arrays
 * indicating the actions, whether it is done, the type, and the counter.
 */
public class TaskList {
    public static String[] actions;
    public static boolean[] isDone;
    public static String[] type;
    public static int counter;
    public static String[] dueString = new String[100];
    public static LocalDateTime[] startTime = new LocalDateTime[100];
    public  static LocalDateTime[] endTime= new LocalDateTime[100];


    /**
     * Constructor of the TaskList
     * @param actions Array of actions.
     * @param isDone Array of whether the action is done.
     * @param type Array of the different type of actions.
     * @param counter Number of action in tasklist.
     */
    public TaskList(String[] actions, boolean[] isDone, String[] type, int counter){
        TaskList.actions = actions;
        TaskList.isDone = isDone;
        TaskList.type = type;
        TaskList.counter = counter;
    }

    /**
     * Formats and saves the to do type of action.
     * @param action String of name of action.
     */

    public static String todo(String action) {
        assert action != null && !action.isEmpty() : "Action description cannot be null or empty";
        isDone[counter] = false;
        actions[counter] = action;
        type[counter] = "T";
        counter= counter + 1;
        String response = "";
        response = "Got it. I've added this task:\n" + helper(action, "T", false) + "\nNow you have " + counter + " tasks in the list.";
        assert counter >= 0 : "Counter should not be negative after adding a task";
        Storage.save("data/duke.txt", actions, type, isDone, dueString, startTime, endTime, counter);

        return response;
    }


    /**
     * Helper method to format the type of action.
     * @param task Name of task.
     * @param taskType Type of task.
     * @param isDone True if done, false if not done.
     * @return String with formatted type of tasktype.
     */
    public static String helper(String task, String taskType, boolean isDone) {
        String taskIcon;
        if (taskType.equals("T")) {
            taskIcon = "[T]";
        } else if (taskType.equals("D")) {
            taskIcon = "[D]";
        } else if (taskType.equals("E")) {
            taskIcon = "[E]";
        } else {
            taskIcon = "[ ]";
        }
        return taskIcon + "[" + (isDone ? "X" : " ") + "] " + task;
    }

    /**
     * Helper method to format and save the deadline type of task.
     * @param action Name of task.
     * @param due String of when the task is due.
     * @param by LocalDateTime of formatted due.
     */
    public static String deadline(String action, String due, LocalDateTime by) {
        assert action != null && !action.isEmpty() : "Action description cannot be null or empty";
        assert due != null && !due.isEmpty() : "Due date description cannot be null or empty";
        assert by != null : "Due date and time should not be null";

        String byString = due.trim();
        dueString[counter] = byString;
        actions[counter] = action;
        type[counter] = "D";
        isDone[counter] = false;
        counter = counter + 1;
        String response = "";
        response = "Got it. I've added this task: \n" + helper(action, "D", false) + " (by" + " " + byString + ")" + "\n Now you have " + counter + " tasks in the list.";
        assert counter >= 0 : "Counter should not be negative after adding a task";

        Storage.save("data/duke.txt", actions, type, isDone, dueString, startTime, endTime, counter);
        return response;
    }

    /**
     * Helper to format and save action of event type.
     * @param action Name of action.
     * @param from String indicating start time.
     * @param to String indicating the end time.
     */
    public static String event(String action, String from, String to) {
        assert action != null && !action.isEmpty() : "Action description cannot be null or empty";
        assert from != null && !from.isEmpty() : "Start time description cannot be null or empty";
        assert to != null && !to.isEmpty() : "End time description cannot be null or empty";

        String response = "";
        LocalDateTime startTimeEvent;
        LocalDateTime endTimeEvent;

        if (from.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{4}$") && to.matches("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{4}$")) {
            startTimeEvent = parseDateTime(from);
            endTimeEvent = parseDateTime(to);
        } else {
            startTimeEvent = parseMonTime(from);
            endTimeEvent = parseMonTime(to);
        }

        startTime[counter] = startTimeEvent;
        endTime[counter] = endTimeEvent;
        actions[counter] = action;
        isDone[counter] = false;
        type[counter] = "E";
        counter = counter + 1;

        assert counter >= 0 : "Counter should not be negative after adding a task";

        response = "Got it. I've added this task:\n" + helper(action, "E", false) + " (from: " + from + " to: " + to + ")" + "\nNow you have " + counter + " tasks in the list.";
        Storage.save("data/duke.txt", actions, type, isDone, dueString, startTime, endTime, counter);
        return response;
    }

    public static LocalDateTime parseMonTime(String monTime) {
        // Split the input string into the day and time parts
        String[] parts = monTime.split(" ");
        String dayOfWeekStr = parts[0];
        String timeStr = parts[1];

        // Parse the day of the week
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayOfWeekStr.toUpperCase());

        // Parse the time in 12-hour format
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("ha", Locale.US);
        LocalTime time = LocalTime.parse(timeStr, timeFormatter);

        // Calculate the number of days to add to get to the specified day of the week
        int daysToAdd = (dayOfWeek.getValue() + 7 - DayOfWeek.MONDAY.getValue()) % 7;

        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Calculate the target date and time
        LocalDateTime targetDateTime = now.plusDays(daysToAdd).withHour(time.getHour()).withMinute(time.getMinute());

        return targetDateTime;
    }



    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTimeStr, formatter);
    }


    public static String list(TaskList taskList) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < taskList.counter; i++) {
            String DMYString = "";

            if (taskList.type[i].equals("D")) {
                DMYString = "by " + taskList.dueString[i];
            } else if (taskList.type[i].equals("E")) {
                DMYString = "from " + taskList.startTime[i] + " to " + taskList.endTime[i];
            }

            response.append((i + 1) + "." + taskList.helper(taskList.actions[i], taskList.type[i], taskList.isDone[i]) + " " + DMYString);

            // Append a newline character after each task except the last one
            if (i < taskList.counter - 1) {
                response.append("\n");
            }
        }

        return response.toString();
    }


    /**
     * Helper method to format the string into the LocalDateTime format.
     * @param dateTimeStr String of the to-be-formatted date time.
     * @return Formatted LocalDateTime version.
     */
    private static LocalDateTime helper2 (String dateTimeStr) {
        DateTimeFormatter DMYhelper = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        // HHmm for the hour and minutes
        return LocalDateTime.parse(dateTimeStr, DMYhelper);
    }
}