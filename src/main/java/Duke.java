import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String name = "Beary";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(String.format("Hello! I'm %s\nWhat can I do for you?", name));
        printLine();

        while (true) {
            String command = scanner.nextLine();
            String taskMessage = command;
            printLine();

            // check for multiple words in command
            String[] words = command.split(" ");
            if (words.length > 1) {
                command = words[0];
            }

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }

            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task currentTask = tasks.get(i);
                    boolean done = currentTask.getDone();
                    System.out.println((i + 1) + "." + (done ? "[X] " : "[ ] ") + currentTask.getName());
                }
                printLine();
                continue;
            }

            if (command.equals("mark")) {
                int taskNumber = Integer.parseInt(words[1]);
                tasks.get(taskNumber - 1).markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + tasks.get(taskNumber - 1).getName());
                printLine();
                continue;
            }

            if (command.equals("unmark")) {
                int taskNumber = Integer.parseInt(words[1]);
                tasks.get(taskNumber - 1).markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[ ] " + tasks.get(taskNumber - 1).getName());
                printLine();
                continue;
            }

            System.out.println("added: " + taskMessage);
            tasks.add(new Task(taskMessage));
            printLine();
        }
    }


    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}

