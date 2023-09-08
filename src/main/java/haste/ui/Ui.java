package haste.ui;

import haste.data.TaskList;
import haste.tasks.Task;

/**
 * Represents a user interface that prints information ot user.
 */
public class Ui {
    public static final String LINE = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    public static final String INDENT = "    ";

    public static final String FORMATLINE = INDENT + LINE + "\n";

    private boolean isRunning = true;


    public static String formatPrint(String input) {
        //System.out.println(INDENT + LINE);
        StringBuilder output = new StringBuilder();
        String[] lines = input.split("\n");
        for (String line : lines) {
            output.append(INDENT).append(line).append("\n");
        }
        return output.toString();

    }
    public String greet() {
        return formatPrint("Hello! I'm HASTE\nWhat can I do for you?");
    }
    public String bye() {
        return formatPrint("Bye. Hope to see you again!");
        //this.running = false;
    }

    public String mark(String taskDesc, TaskList tasks) {
        return formatPrint("Nice! I've marked this task as done:\n" + taskDesc);
    }

    public String unmark(String taskDesc, TaskList tasks) {
        return formatPrint("Okay, I've marked this task as not done:\n" + taskDesc);
    }
    public String delete(String taskDesc, TaskList tasks) {
        return formatPrint("Noted. I've removed this task\n" + taskDesc + "\nNow you have "
                + tasks.getNumOfTasks() + " tasks in the list");

    }
    public String add(String taskDesc, TaskList tasks) {
        return formatPrint("Got it. I've added this task:\n" + taskDesc + "\nNow you have "
                + tasks.getNumOfTasks() + " tasks in the list.");
    }

    public String printList(TaskList tasks) {
        StringBuilder listOfTasks = new StringBuilder();
        for (Task x : tasks.getTasks()) {
            listOfTasks.append(INDENT).append(tasks.getTasks().indexOf(x) + 1).append(". ").append(x).append("\n");
        }
        return listOfTasks.toString();
    }

    public boolean getRunning() {
        return isRunning;
    }

}
