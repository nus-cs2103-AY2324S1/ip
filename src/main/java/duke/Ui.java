package duke;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Builds output strings to show to the user.
 */

public class Ui {
    private DtFormat dtf;

    /**
     * Constructor for the Ui class.
     *
     * @param dtf DateTimeFormatter to be used in formatting strings to be displayed.
     */

    public Ui(DtFormat dtf) {
        this.dtf = dtf;
    }

    /**
     * Prints a message to the UI.
     *
     * @param msg string to be printed
     */

    public String print(String msg) {
        return msg + "\n";
    }

    /**
     * Formats the information of a TaskType into a string to be shown in the UI.
     *
     * @param task TaskType to be formatted.
     * @param fmt DateTimeFormatter to be used in producing the output
     * @returns String representation of the TaskType to be shown in the UI
     */

    public String formatTaskToPrint(TaskType task, DateTimeFormatter fmt) {
        String completedBox = "[" + (task.getIsCompleted() ? "X" : " ") + "] ";
        String taskTypeBox = "[" + task.toShortString() + "]";
        String formattedDatetime = task.getFormattedDatetime(fmt);
        Priority priority = task.getPriority();
        String priorityBox = buildPriorityBox(priority);
        return taskTypeBox + completedBox + " " + task.getTaskDesc() + " " + formattedDatetime + priorityBox;
    }

    /**
     * Builds a priority box to be shown to the user based on given Priority.
     *
     * @param p Priority pf task to be converted to a box
     * @returns a box (string) representing priority of task
     */
    private String buildPriorityBox(Priority p) {
        if (p == Priority.HIGH) {
            return " [Priority: High]";
        }
        return "";
    }

    /**
     * Prints all TaskTypes in a list to the UI.
     *
     * @param items items to be printed
     */

    public String printItems(ArrayList<TaskType> items) {
        String out = "";
        for (int i = 0; i < items.size(); i++) {
            out += (i + 1) + ". " + formatTaskToPrint(items.get(i), dtf.getOutFormatter());
            out += "\n";
        }
        return out;
    }

    /**
     * Prints the introductory message to the end-user.
     */

    public static String getIntroMessage() {
        return "Hello, I'm Duke the Robot! How can I help you?\n";
    }

    /**
     * Prints the final message to the end-user when they exit the program.
     */

    public static String getExitMessage() {
        return "Bye! Hope to see you again soon!\n";
    }

    /**
     * Displays a loading error to the end-user when data cannot be loaded for some reason.
     */

    public String showLoadingError() {
        return "An error occurred while loading data into your list, starting with a blank list...";
    }

}


