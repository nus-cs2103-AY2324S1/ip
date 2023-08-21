import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    static List<Task> taskList = new ArrayList<>();
    static void greet() {
        String greeting = "_________________________________________________\n"
                + " Hello! I'm Glub!\n"
                + " What can I do for you?\n"
                + "_________________________________________________\n";
        System.out.println(greeting);
    }

    static void addTask(String task, String type) {
        if (type.equals("todo")) {
            taskList.add(new ToDo(task));
        } else if (type.equals("deadline")) {
            String[] taskPortions = task.split("/");
            String desc = taskPortions[0];
            String deadline = taskPortions[1].split(" ", 2)[1];
            taskList.add(new Deadline(desc, deadline));
        } else {
            String[] taskPortions = task.split("/");
            String desc = taskPortions[0];
            String start = taskPortions[1].split(" ", 2)[1];
            String end = taskPortions[2].split(" ", 2)[1];
            taskList.add(new Event(desc, start, end));
        }
        String addMsg = "_________________________________________________\n"
                + " Got it. I've added this task:\n"
                + String.format(" \t%s\n", taskList.get(taskList.size()-1))
                + String.format("Now you have %d tasks in the list\n", taskList.size())
                + "_________________________________________________\n";
        System.out.println(addMsg);
    }

    static void list() {
        System.out.println(" Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf(" %d. %s\n", i + 1, taskList.get(i));
        }
    }

    static void mark(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.setDone();
        String markMsg = "_________________________________________________\n"
                + " Nice! I've marked this task as done:\n"
                + String.format("\t %s\n", task)
                + "_________________________________________________\n";
        System.out.println(markMsg);
    }

    static void unmark(int taskNum) {
        Task task = taskList.get(taskNum - 1);
        task.setUndone();
        String markMsg = "_________________________________________________\n"
                + " Ok, I've marked this task as not done yet:\n"
                + String.format("\t %s\n", task)
                + "_________________________________________________\n";
        System.out.println(markMsg);
    }
    static void exit() {
        String exitMsg = "_________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "_________________________________________________\n";
        System.out.println(exitMsg);
        System.exit(0);
    }
    public static void main(String[] args) {
        greet();
        while (true) {
            Scanner inputScanner = new Scanner(System.in);
            String command = inputScanner.next();
            if (command.equals("bye")) {
                exit();
            } else if (command.equals("list")) {
                list();
            } else if (command.equals("mark")) {
                mark(inputScanner.nextInt());
            } else if (command.equals("unmark")) {
                unmark(inputScanner.nextInt());
            } else {
                String task = inputScanner.nextLine();
                addTask(task, command);
            }
        }
    }
}
