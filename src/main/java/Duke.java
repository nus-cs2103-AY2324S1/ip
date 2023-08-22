import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> taskList = new ArrayList<>();

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addTask(String task) {
        taskList.add(task);
        System.out.println("Added: " + task);
    }

    public static void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | | / _ \\\n"
                + "| |_| | |_| |  |_   __/\n"
                + "|____/ \\__,_|___|\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.print("> ");
            userInput = scanner.nextLine();
            System.out.println(userInput);
            if (userInput.equalsIgnoreCase("list")) {
                listAllTasks();
            } else if (!userInput.equalsIgnoreCase("bye")) {
                addTask(userInput);
            }
        } while (!userInput.equalsIgnoreCase("bye"));

        exit();
        scanner.close();
    }
}
