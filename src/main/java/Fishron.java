import java.util.Scanner;

public class Fishron {
    private String name;
    private String[] storage = new String[100];
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

    public void addTask(String task) {
        storage[taskCount] = task;
        taskCount++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + storage[i]);
        }
        System.out.println("____________________________________________________________");
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
            } else if (!input.equalsIgnoreCase("bye")) {
                chatBot.addTask(input);
            }
        } while (!input.equalsIgnoreCase("bye"));

        chatBot.farewell();
        System.out.println("____________________________________________________________");
    }
}
