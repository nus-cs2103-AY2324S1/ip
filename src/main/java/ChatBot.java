import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {
    static final String name = "4F5DA2";
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);
    static final int maxNumberOfTasks = 100;

    static ArrayList<Task> taskList = new ArrayList<>(maxNumberOfTasks);

    public static void greet() {
        System.out.println(line);
        System.out.println("\tWelcome back, human!");
        System.out.println("\tI'm your personal ChatBot, " + name + ".");
        System.out.println("\tWhat can I do for you today?");
        System.out.println(line);
    }

    public static void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void output(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }

    public static void addTask(String name) {
        taskList.add(new Task(name));
        output(String.format("\tadded: %s", name));
    }

    public static void markAs(boolean isDone, int index) {
        Task task = taskList.get(index-1);
        task.markAs(isDone);
        output(String.format("\t%s\n\t[%s] %s",
                isDone ? "Nice! I've marked this task as done:"
                       : "OK, I've marked this task as not done yet:",
                isDone ? "X" : " ",
                task.getName()));
    }

    public static void listTasks() {
        System.out.println(line);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(String.format("\t%d.[%s] %s",
                    i+1,
                    task.checkIsDone() ? "X" : " ",
                    task.getName()));
        }
        System.out.println(line);
    }

    public static void nextCommand() {
        String command = scanner.nextLine();
        if (command.equals("bye")) {
            exit();
        } else if (command.equals("list")) {
            listTasks();
            nextCommand();
        } else {
            String[] words = command.split(" ");
            if (words[0].equals("mark") || words[0].equals("unmark")) {
                markAs(words[0].equals("mark"), Integer.parseInt(words[1]));
            } else {
                addTask(command);
            }
            nextCommand();
        }
    }

    public static void main(String[] args) {
        greet();
        nextCommand();

    }
}
