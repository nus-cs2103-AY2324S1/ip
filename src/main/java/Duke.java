import java.util.Scanner;

public class Duke {
    private final static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public void startChat() {
        System.out.println("---------------------------------------------");
        System.out.println(" Hello! I'm zy");
        System.out.println(" What can I do for you?");
        System.out.println("---------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("---------------------------------------------");
            this.readNewTask(command);
            System.out.println("---------------------------------------------");
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("---------------------------------------------");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------");
    }

    public void readNewTask(String command) {
        String[] commandSplit = command.split(" ");
        if (command.equals("list")) {
            this.listAllTasks();
        } else {
            if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                this.addNewTask(command);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                int markTask = Integer.parseInt(commandSplit[1]);
                if (command.startsWith("mark")) {
                    tasks[markTask - 1].markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                } else if (command.startsWith("unmark")) {
                    tasks[markTask - 1].markAsUndone();
                    System.out.println(" OK, I've marked this task as not done yet:");
                }
                System.out.println("   " + tasks[markTask - 1].toString());
            }
        }
    }

    public void listAllTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i].toString());
        }
    }

    public void addNewTask(String command) {
        if (command.startsWith("todo")) {
            String taskDescription = command.substring(5);
            tasks[taskCount] = new ToDo(taskDescription);
            taskCount++;
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1].toString());
        } else if (command.startsWith("deadline")) {
            String[] parts = command.split("/by");
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            tasks[taskCount] = new Deadline(description, by);
            taskCount++;
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1].toString());
        } else if (command.startsWith("event")) {
            String[] parts = command.split("/from");
            String description = parts[0].substring(6).trim();
            String[] timeParts = parts[1].split("/to");
            String start = timeParts[0].trim();
            String end = timeParts[1].trim();
            tasks[taskCount] = new Event(description, start, end);
            taskCount++;
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1].toString());
        }
        System.out.println(" Now you have " + taskCount + " tasks in the list.");
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.startChat();
    }
}
