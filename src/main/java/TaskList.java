import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskList {
    public static String[] actions;
    public static boolean[] isDone;
    public static String[] type;
    public static int counter;
    public static String[] dueString = new String[100];
    public static LocalDateTime[] startTime = new LocalDateTime[100];
    public  static LocalDateTime[] endTime= new LocalDateTime[100];



    public TaskList(String[] actions, boolean[] isDone, String[] type, int counter){
        TaskList.actions = actions;
        TaskList.isDone = isDone;
        TaskList.type = type;
        TaskList.counter = counter;
    }

    public static void todo(String action) {
            isDone[counter] = false;
            actions[counter] = action;
            type[counter] = "T";
            counter= counter + 1;
            System.out.println("Got it. I've added this task:\n" + helper(action, "T", false));
            System.out.println("Now you have " + counter + " tasks in the list.");
        }


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

    private static LocalDateTime helper2 (String dateTimeStr) {
        DateTimeFormatter DMYhelper = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        // HHmm for the hour and minutes
        return LocalDateTime.parse(dateTimeStr, DMYhelper);
    }
}
