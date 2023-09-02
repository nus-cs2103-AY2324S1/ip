package haste.ui;

import haste.data.TaskList;
import haste.tasks.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

/**
 * Represents a user interface that prints information ot user.
 */
public class Ui {
    public static final String LINE = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    public static final String INDENT = "    ";

    public boolean running = true;


    public static void formatPrint(String input) {
        System.out.println(INDENT + LINE);
        String[] lines = input.split("\n");
        for (String line : lines) {
            System.out.println(INDENT + line);
        }
        System.out.println(INDENT + LINE);
    }
    public void greet() {
        formatPrint("Hello! I'm HASTE\nWhat can I do for you?");
    }
    public void bye() {
        formatPrint("Bye. Hope to see you again!");
        this.running = false;
    }

    public void mark(String taskDesc, TaskList tasks) {
        formatPrint("Nice! I've marked this task as done:\n" + taskDesc);
    }

    public void unmark(String taskDesc, TaskList tasks) {
        formatPrint("Okay, I've marked this task as not done:\n" + taskDesc);
    }
    public void delete(String taskDesc, TaskList tasks) {
        formatPrint("Noted. I've removed this task\n" + taskDesc + "\nNow you have " + tasks.getNumOfTasks() + " tasks in the list");

    }
    public void add(String taskDesc, TaskList tasks) {
        Ui.formatPrint("Got it. I've added this task:\n" + taskDesc + "\nNow you have " + tasks.getNumOfTasks() + " tasks in the list.");
    }

    public void printList(TaskList tasks) {
        System.out.println(INDENT + LINE);
        for (Task x : tasks.taskList) {
            System.out.println(INDENT + (tasks.taskList.indexOf(x) + 1) + ". " + x);
        }
        System.out.println(INDENT + LINE);
    }

}
