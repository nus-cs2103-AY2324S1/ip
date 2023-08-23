import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String name = "SoCrates";
    private static String line =
        "\t____________________________________________________________";
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> tasks = new ArrayList<>();
    private boolean isRunning = true;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        printWelcome();

        while (isRunning) {
            runCommand();
        }

        printExit();
    }

    private void runCommand() {
        String command = scanner.nextLine().trim();

        String[] args = command.split(" ");

        if (args[0].equals("bye")) {
            isRunning = false;
            return;
        } else if (args[0].equals("list")) {
            printTasks();
            return;
        } else if (args[0].equals("todo") && args.length > 1) {
            String description = command.replaceFirst("todo ", "");
            if (description.equals("")) {
                return;
            }
            ToDo toDo = new ToDo(description);
            addToTasks(toDo);
            return;
        } else if (args[0].equals("mark")) {
            int taskNumber = Integer.parseInt(args[1]);
            doTask(taskNumber);
            return;
        } else if (args[0].equals("unmark")) {
            int taskNumber = Integer.parseInt(args[1]);
            undoTask(taskNumber);
            return;
        } else if (args[0].equals("deadline") && args.length > 1) {
            if (!command.contains(" /")) {
                return;
            }

            String by = command.split(" /by ", 2)[1];
            String description = command
                    .replaceFirst("deadline ", "")
                    .replaceFirst(" /by " + by, "");
            Deadline deadline = new Deadline(description, by);
            addToTasks(deadline);
            return;
        } else if (args[0].equals("event") && args.length > 1) {
            if (!command.contains(" /from ") || !command.contains(" /to ")) {
                return;
            }

            String to = command.split(" /to ", 2)[1];
            command = command.replaceFirst(" /to " + to, "");

            String from = command.split(" /from ", 2)[1];

            String description = command
                    .replaceFirst("event ", "")
                    .replaceFirst(" /from " + from, "");
            Event event = new Event(description, from, to);
            addToTasks(event);
            return;
        }

        printMessage("Unrecognized command. Try again?");
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

    private void addToTasks(Task task) {
        tasks.add(task);
        String[] message = {
                "Go it. I've added this task:",
                "\t" + task,
                "Now you have " + tasks.size() + " tasks in the list."
        };
        printMessage(message);
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

    private void undoTask(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            return;
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();

        String[] message = {
                "Ok, I've marked this task as not done yet:",
                "\t " + task
        };

        printMessage(message);
    }

}
