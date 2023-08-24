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
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task task = storage[i];
            int count = i + 1;
            System.out.println(count + "." + task.toString());
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
            try {
            if (input.toLowerCase().startsWith("todo")) {
                String[] description = input.split("todo");
                if (description.length < 2) {
                    throw new FishronException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                chatBot.addTask(new ToDo(description[1]));
            } else if (input.toLowerCase().startsWith("deadline")) {
                String[] parts = input.split("/by");
                if (parts.length != 2) {
                    throw new FishronException("☹ OOPS!!! Please provide a valid deadline format.");
                }
                chatBot.addTask(new Deadline(parts[0].split("deadline")[1], parts[1]));
            } else if (input.toLowerCase().startsWith("event")) {
                String[] parts = input.split("/from|/to");
                if (parts.length != 3) {
                    throw new FishronException("☹ OOPS!!! Please provide a valid event format.");
                }
                chatBot.addTask(new Event(parts[0].split("event")[1], parts[1], parts[2]));
            } else if (input.equalsIgnoreCase("list")) {
                chatBot.listTasks();
            } else if (input.toLowerCase().startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                chatBot.markTaskAsDone(taskIndex);
            } else if (input.toLowerCase().startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]);
                chatBot.unmarkTask(taskIndex);
            } else if (!input.equalsIgnoreCase("bye")) {
                throw new FishronException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            } catch (FishronException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        } while (!input.equalsIgnoreCase("bye")) ;

            chatBot.farewell();
            System.out.println("____________________________________________________________");
    }
}
