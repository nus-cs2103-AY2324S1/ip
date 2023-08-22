import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addTask(String description) {
        taskList.add(new Task(description));
        System.out.println("added: " + description);
    }

    public static void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task.getDescription());
            }
        }
    }

    public static void markTask(int i) {
        Task targetTask = taskList.get(i - 1);
        if (targetTask.getMarkedStatus()) {
            System.out.println("Already marked!");
        } else {
            targetTask.mark();
            System.out.println("Nice! I've marked this task as done:\n  " + targetTask.getDescription());
        }
    }

    public static void unMarkTask(int i) {
        Task targetTask = taskList.get(i - 1);
        if (targetTask.getMarkedStatus()) {
            targetTask.unMark();
            System.out.println("I've unmarked this task:\n  " + targetTask.getDescription());
        } else {
            System.out.println("Already unmarked");
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
        boolean ongoing = true;
//        while (!userInput.equalsIgnoreCase("bye")) {
        while(ongoing) {
            System.out.print("> ");
            userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                listAllTasks();
            } else if (userInput.equals("bye")) {
                ongoing = false;
            } else if (userInput.startsWith("mark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length != 2) {
                    addTask(userInput);
                } else {
                    int taskIndex = Integer.parseInt(parts[1]);
                    markTask(taskIndex);
                }
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ");
                if (parts.length != 2) {
                    addTask(userInput);
                } else {
                    int taskIndex = Integer.parseInt(parts[1]);
                    unMarkTask(taskIndex);
                }
            } else {
                addTask(userInput);
            }
        }

        exit();
        scanner.close();
    }
}
