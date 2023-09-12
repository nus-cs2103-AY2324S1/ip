package io;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * The Ui class displays information to the user
 */
public class Ui {


    private ArrayList<String> printArray;


    public Ui() {
        printArray = new ArrayList<>();
    }

    /**
     * Adds input string to the print array.
     *
     * @param input the String to add to the print array
     */
    public void addPrintStatement(String input) {
        printArray.add(input);
    }

    /**
     * Returns everything in the print array as a single string. Also resets the print array
     *
     * @return A string representing the output from duke.
     */
    public String getPrintStatement() {
        StringBuilder output = new StringBuilder();
        for (String x : printArray) {
            output.append(x).append("\n");
        }
        printArray.clear();
        return output.toString();
    }

    /**
     * Displays the greeting message to the user
     */
    public void displayGreetings() {
        String greeting = "Hello! I'm KimochiUsagi (きもち　うさぎ)!\n";
        String info = "Ask the bunny a question!\n";
        addPrintStatement(greeting);
        addPrintStatement(info);
    }


    /**
     * Displays a message and the display of a given Task one after the other.
     *
     * @param message The message to be displayed.
     * @param task    The Task object whose display will be shown.
     */
    public void displayAction(String message, Task task) {

        addPrintStatement(message);
        addPrintStatement(displayTask(task));

    }


    /**
     * Displays the goodbye text.
     */
    public void displayGoodbye() {
        String goodbye = "Bye. See you again! (またね)";
        addPrintStatement(goodbye);
    }

    /**
     * Generates and returns the appropriate display string for the given Task object, based on its
     * subtype.
     *
     * @param task The Task object for which to generate the display.
     * @return The display string representing the given task.
     */
    public String displayTask(Task task) {
        String answer = "";

        if (task instanceof Todo) {
            answer =
                "[" + Todo.taskType + "]" + "[" + task.getDoneIcon() + "] " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadTask = (Deadline) task;

            answer =
                "[" + Deadline.TASK_TYPE + "]" + "[" + task.getDoneIcon() + "] "
                    + task.getDescription()
                    + "("
                    + deadTask.getDeadDate() + ")";

        } else if (task instanceof Event) {
            Event eventTask = (Event) task;

            answer =
                "[" + Event.taskType + "]" + "[" + task.getDoneIcon() + "] " + task.getDescription()
                    + "("
                    + eventTask.getStartDate() + " to " + eventTask.getEndDate() + ")";
        }

        return answer;
    }

}
