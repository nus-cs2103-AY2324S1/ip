package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {

    protected static final String INDENT = "     ";
    protected static final String NAME = "404";
    private static final String NAME_ART =
            "               _                _               _                      \n"
            + "           _  /\\ \\            / /\\          _  /\\ \\               \n"
            + "          /\\_\\\\ \\ \\          / /  \\        /\\_\\\\ \\ \\        \n"
            + "         / / / \\ \\ \\        / / /\\ \\      / / / \\ \\ \\          \n"
            + "        / / /   \\ \\ \\      / / /\\ \\ \\    / / /   \\ \\ \\        \n"
            + "        \\ \\ \\____\\ \\ \\    /_/ /  \\ \\ \\   \\ \\ \\____\\ \\ \\ \n"
            + "         \\ \\________\\ \\   \\ \\ \\   \\ \\ \\   \\ \\________\\ \\ \n"
            + "          \\/________/\\ \\   \\ \\ \\   \\ \\ \\   \\/________/\\ \\  \n"
            + "                    \\ \\ \\   \\ \\ \\___\\ \\ \\            \\ \\ \\ \n"
            + "                     \\ \\_\\   \\ \\/____\\ \\ \\            \\ \\_\\ \n"
            + "                      \\/_/    \\_________\\/             \\/_/";

    private Scanner sc;

    public void showWelcome() {
        String greeting = String.format("%sHello! I'm %s%n%sWhat can I do for you?",
                INDENT, NAME, INDENT);
        System.out.println(NAME_ART);
        showLine();
        System.out.println(greeting);
        showLine();
        System.out.println();
    }
    public void showLoadingError() {
        String goneWrongMessage =
                String.format("%sOOPS!!!Something terrible happened to the data file.\n"
                              + "%sDon't worry I will clean up the mess!", INDENT, INDENT);
        showLine();
        System.out.println(goneWrongMessage);
        showLine();
    }

    public String readCommand() {
        if (this.sc == null) {
            this.sc = new Scanner(System.in);
        }
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.printf("%s%s\n", INDENT, message);
    }

    public void showExit() {
        this.sc.close();
        System.out.printf("%sBye. Hope to see you again soon!%n", INDENT);
    }

    public void showLine() {
        String line = "    ____________________________________________________________\n"
                + "   /_____/_____/_____/_____/_____/_____/_____/_____/_____/_____/";
        System.out.println(line);
    }

    public void showAddTask(Task task, int taskListSize) {
        System.out.printf("%sGot it. I've added this task:\n"
                          + "%s  %s\n"
                          + "%sNow you have %d tasks in the list.\n",
                INDENT, INDENT, task, INDENT, taskListSize);
    }

    public void showDeleteTask(Task removedTask, int taskListSize) {
        System.out.printf("%sNoted. I've removed this task:%n"
                          + "%s  %s%n"
                          + "%sNow you have %d tasks in the list.%n",
                INDENT, INDENT, removedTask, INDENT, taskListSize);
    }

    public void showMarkTask(boolean isMark, String task) {
        String message = isMark
                ? "Nice! I've marked this task as done:"
                : "OK, I've marked this task as not done yet:";
        System.out.printf("%s%s\n%s  %s\n", INDENT, message, INDENT, task);
    }

    public void showManipulateAllTask(String keyword) {
        System.out.printf("%sNoted. I will %s all tasks.\n", INDENT, keyword);
    }

    public void showListTask(String[] tasks) {
        System.out.printf("%sHere are the tasks in your list:\n", INDENT);
        for (int i = 0; i < tasks.length; i++) {
            System.out.printf("%s%d.%s\n", INDENT, i + 1, tasks[i]);
        }
    }

    public void showPrintDateTask(String[] tasksOnDate, String date) {
        System.out.printf("%sHere are the %d tasks happening on %s:\n",
                INDENT, tasksOnDate.length, date);
        for (String task : tasksOnDate) {
            System.out.printf("%s  %s\n", INDENT, task);
        }
    }

    public void showFindTask(String[] tasksFound, String[] indices) {
        System.out.printf("%sHere are the matching tasks in your list:\n", INDENT);
        for (int i = 0; i < tasksFound.length; i++) {
            System.out.printf("%s%s.%s\n", INDENT, indices[i], tasksFound[i]);
        }
    }

    public static String connectTwoLine(String lineOne, String lineTwo) {
        return String.format("%s\n%s%s", lineOne, INDENT, lineTwo);
    }
}
