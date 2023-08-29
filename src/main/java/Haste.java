import java.util.Scanner;

import static java.lang.Integer.parseInt;
public class Haste {
    public static final String LINE = "＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿";
    public static final String INDENT = "    ";

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
                TaskList.printList();
            } else if (cmd.startsWith("mark")) {
                mark(cmd);
            } else if (cmd.startsWith("unmark")) {
                unmark(cmd);
            } else if (cmd.startsWith("delete")) {
                delete(cmd);
            } else {
                // add task to list
                try {
                    TaskList.addTask(cmd);
                    Haste.printLine();
                    System.out.println(Haste.INDENT + "Got it. I've added this task:");
                    System.out.println(Haste.INDENT + TaskList.getTask(TaskList.getNumOfTasks() - 1));
                    System.out.println(Haste.INDENT + "Now you have " + TaskList.getNumOfTasks() + " tasks in the list.");
                    Haste.printLine();

                } catch(EmptyTaskException e) {
                    e.printStackTrace();
                } catch(InvalidCommand e) {
                    e.printStackTrace();
                }

            }

        }
    }

    public static void greet() {
        printLine();
        System.out.println(INDENT + "Hello! I'm HASTE\n" + INDENT + "What can I do for you?");
        printLine();
        // read file and update tasklist
        TaskList.read();
    }

    public static void bye() {
        printLine();
        System.out.println(INDENT + "Bye. Hope to see you again!");
        printLine();
        // add functionality: call tasklist method to save data in a textfile
        TaskList.save();
    }

    public static void mark(String cmd) {
        int id = parseInt(cmd.split(" ")[1]) - 1;
        Task currTask = TaskList.taskList.get(id);
        currTask.markDone();
        printLine();
        System.out.println(INDENT + "Nice! I've marked this task as done!:");
        System.out.println(INDENT + currTask);
        printLine();
    }

    public static void unmark(String cmd) {
        int id = parseInt(cmd.split(" ")[1]) - 1;
        Task currTask = TaskList.taskList.get(id);
        currTask.markUndone();
        printLine();
        System.out.println(INDENT + "Okay, I've marked this task as not done:");
        System.out.println(INDENT + currTask);
        printLine();
    }

    public static void delete(String cmd) {
        int id = parseInt(cmd.split(" ")[1]) - 1;
        printLine();
        // to do : fix order (delete task to be in front?)
        System.out.println(INDENT + "Noted. I've removed this task");
        System.out.println(INDENT + TaskList.taskList.get(id));
        TaskList.deleteTask(id);
        System.out.println(INDENT + "Now you have " + TaskList.getNumOfTasks() + " tasks in the list");
        printLine();
    }


    public static void printLine() {
        System.out.println(INDENT + LINE);
    }



}
