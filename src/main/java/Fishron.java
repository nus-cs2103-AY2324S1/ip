import java.util.Scanner;

public class Fishron {
    private String name;
    private Task[] storage = new Task[100];
    private int taskCount = 0;

    public Fishron(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void addTask(Task task) {
        storage[taskCount] = task;
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task.getDescription());
        System.out.println("____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task task = storage[i];
            System.out.println((i + 1) + ". " + task.getStatusIcon() + " " + task.getDescription());
        }
        System.out.println("____________________________________________________________");
    }

    public void markTaskAsDone(int Index) {
        if (Index >= 1 && Index <= taskCount) {
            Task task = storage[Index - 1];
            task.markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void unmarkTask(int Index) {
        if (Index >= 1 && Index <= taskCount) {
            Task task = storage[Index - 1];
            task.markAsUndone();
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void main(String[] args) {
        String botName = "Fishron";
        Fishron chatBot = new Fishron(botName);

        chatBot.greet();
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("list")) {
                chatBot.listTasks();
            } else if (input.toLowerCase().startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                chatBot.markTaskAsDone(taskIndex);
            } else if (input.toLowerCase().startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                chatBot.unmarkTask(taskIndex);
            } else if (!input.equalsIgnoreCase("bye")) {
                chatBot.addTask(new Task(input));
            }
        } while (!input.equalsIgnoreCase("bye"));

        chatBot.farewell();
        System.out.println("____________________________________________________________");
    }
}
