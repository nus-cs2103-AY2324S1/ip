import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
public class Haste {
    public static final String LINE = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    public static final String INDENT = "    ";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        boolean online = true;
        Scanner sc = new Scanner(System.in);
        while (online) {
            String cmd = sc.nextLine();
            // if str is bye
            if (cmd.equals("bye")) {
                bye();
                online = false;
            } else if (cmd.equals("list")) {
                // print list

                printLine();
                for (Task a : taskList) {
                    System.out.println(INDENT + a.id + ". " + a);
                }
                printLine();

            } else if (cmd.startsWith("mark")) {
                mark(cmd);

            } else if (cmd.startsWith("unmark")) {
                unmark(cmd);

            } else {
                // add task to list
                Task newTask = CmdHandler.interpret(cmd);
                add(newTask);
            }

        }

    }

    public static void greet() {
        printLine();
        System.out.println(INDENT + "Hello! I'm HASTE\n" + INDENT + "What can I do for you?");
        printLine();
    }

    public static void bye() {
        printLine();
        System.out.println(INDENT + "Bye. Hope to see you again!");
        printLine();
    }

    public static void mark(String cmd) {
        int id = parseInt(cmd.split(" ")[1]) - 1;
        Task currTask = taskList.get(id);
        currTask.markDone();
        printLine();
        System.out.println(INDENT + "Nice! I've marked this task as done!:");
        System.out.println(INDENT + currTask);
        printLine();
    }

    public static void unmark(String cmd) {
        int id = parseInt(cmd.split(" ")[1]) - 1;
        Task currTask = taskList.get(id);
        currTask.markUndone();
        printLine();
        System.out.println(INDENT + "Okay, I've marked this task as not done:");
        System.out.println(INDENT + currTask);
        printLine();
    }

    public static void add(Task newTask) {
        taskList.add(newTask);
        printLine();
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + newTask);
        System.out.println(INDENT + "Now you have " + Task.numOfTasks + " tasks in the list.");
        printLine();
    }

    public static void printLine() {
        System.out.println(INDENT + LINE);
    }








}
