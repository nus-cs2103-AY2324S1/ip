import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String name = "SoCrates";
    private static String line =
        "\t____________________________________________________________";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        printWelcome();

        while (true) {
            String command = scanner.nextLine();
            String[] args = command.split(" ");

            if (args[0].equals("bye")) {
                break;
            } else if (args[0].equals("list")) {
                printTasks();
            } else if (args[0].equals("done")) {
                int target = Integer.parseInt(args[1]);
                doTask(target);
            } else {
                addToTasks(command);
            }
        }

        printExit();
    }

    private static void printMessage(String message) {
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println("\n" + line + "\n");
    }

    private static void printMessage(String[] message) {
        System.out.println(line);
        for (String messageLine : message) {
            System.out.println("\t" + messageLine);
        }
        System.out.println("\n" + line + "\n");
    }

    private static void printWelcome() {
        String[] message = {
                "Hello! I'm " + name,
                "What can I do for you?"
        };

        printMessage(message);
    }

    private static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }

    private void addToTasks(String text) {
        Task task = new Task(text);
        tasks.add(task);
        printMessage("added: " + task);
    }

    private void printTasks() {
        System.out.println(line);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
        }

        System.out.println("\n" + line + "\n");
    }

    private void doTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            return;
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();

        String[] message = {
                "Nice! I've marked this task as done:",
                "\t " + task
        };

        printMessage(message);
    }

}
