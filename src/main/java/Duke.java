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

    static void addTask(String task) {
        taskList.add(new Task(task));
        String addMsg = "_________________________________________________\n"
                + String.format(" added: %s\n", task)
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
            String input = inputScanner.next();
            if (input.equals("bye")) {
                exit();
            } else if (input.equals("list")) {
                list();
            } else if (input.equals("mark")) {
                mark(inputScanner.nextInt());
            } else {
                addTask(input);
            }
        }
    }
}
