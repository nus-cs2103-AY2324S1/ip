import java.util.Scanner;
public class Harvard {
    public static void main(String[] args) {
        String line = "----------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm Harvard\nWhat can I do for you?");
        System.out.println(line);

        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            } else if (command.startsWith("todo")) {
                String description = command.substring(5);
                Todo todo = new Todo(description);
                tasks[taskCount] = todo;
                taskCount++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:\n" + todo +
                        "\nNow you have " + taskCount + " tasks in the list.");
            } else if (command.startsWith("deadline")) {
                String[] split = command.split(" /by ");
                String description = split[0].substring(9);
                String by = split[1];
                Deadline deadline = new Deadline(description, by);
                tasks[taskCount] = deadline;
                taskCount++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:\n" + deadline +
                        "\nNow you have " + taskCount + " tasks in the list.");
            } else if (command.startsWith("event")) {
                String[] split = command.split("/from ");
                String[] split2 = split[1].split(" /to ");
                String description = split[0].substring(6);
                String from = split2[0];
                String to = split2[1];
                Event event = new Event(description, from, to);
                tasks[taskCount] = event;
                taskCount++;
                System.out.println(line);
                System.out.println("Got it. I've added this task:\n" + event +
                        "\nNow you have " + taskCount + " tasks in the list.");
            } else if (command.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (command.startsWith("mark")) {
                String[] split = command.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                if (index >= taskCount || index < 0) {
                    System.out.println(line);
                    System.out.println("Invalid index!");
                    System.out.println(line);
                    continue;
                }
                tasks[index].markAsDone();
                System.out.println(line);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
            } else if (command.startsWith("unmark")) {
                String[] split = command.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                tasks[index].markAsUndone();
                System.out.println(line);
                System.out.println("Ok! I've marked this task as not done yet:");
                System.out.println(tasks[index]);
            } else {
                Task task = new Task(command);
                tasks[taskCount] = task;
                taskCount++;
                System.out.println(line);
                System.out.println("Got it! I've added this task: " + task);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
            System.out.println(line);
        }
        in.close();
    }
}
