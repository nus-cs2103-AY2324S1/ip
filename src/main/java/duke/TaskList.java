package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static void todo(String action) {
        isDone[counter] = false;
        actions[counter] = action;
        type[counter] = "T";
        counter= counter + 1;
        System.out.println("Got it. I've added this task:\n" + helper(action, "T", false));
        System.out.println("Now you have " + counter + " tasks in the list.");
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
    public static void deadline(String action, String due, LocalDateTime by) {
        String byString = due.trim();
        dueString[counter] = byString;
        actions[counter] = action;
        // System.out.println(action + "hello");
        type[counter] = "D";
        isDone[counter] = false;
        counter = counter + 1;
        System.out.println("Got it. I've added this task: \n" + helper(action, "D", false) + " (by" + " " + byString + ")");
        System.out.println("Now you have " + counter + " tasks in the list.");

    }

    /**
     * Helper to format and save action of event type.
     * @param action Name of action.
     * @param start String indicating start time.
     * @param end String indicating the end time.
     */
    public static void event(String action, String start, String end) {
        String from = start.trim();
        String to = end.trim();
        LocalDateTime startTimeEvent = helper2(from);
        LocalDateTime endTimeEvent = helper2(to);
        startTime[counter] = startTimeEvent;
        endTime[counter] = endTimeEvent;
        actions[counter] = action;
        isDone[counter] = false;
        type[counter] = "E";
        counter = counter + 1;
        System.out.println("Got it. I've added this task:\n" + helper(action, "E", false) + " (from: " + from + " to: " + to + ")");
        System.out.println("Now you have " + counter + " tasks in the list.");
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