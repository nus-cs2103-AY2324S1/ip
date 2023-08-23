import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "██╗░░░██╗██████╗░██████╗░░█████╗░██╗\n"
                    + "██║░░░██║██╔══██╗██╔══██╗██╔══██╗██║\n"
                    + "██║░░░██║██████╔╝██████╦╝██║░░██║██║\n"
                    + "██║░░░██║██╔══██╗██╔══██╗██║░░██║██║\n"
                    + "╚██████╔╝██║░░██║██████╦╝╚█████╔╝██║\n"
                    + "░╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>(100);

        while (true) {
            String command = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markDone();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
                } else {
                    System.out.println("Invalid task index.");
                }
            } else {
                tasks.add(new Task(command));
                System.out.println("added: " + command);
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}

class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getDescription() {
        return description;
    }
    public boolean isDone() {
        return isDone;
    }
    public void markDone() {
        isDone = true;
    }
    public void markNotDone() {
        isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
