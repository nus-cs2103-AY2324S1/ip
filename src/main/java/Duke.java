import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    private List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Duke wizzer = new Duke();
        String logo = "Wiz";
        List<Task> tasks = new ArrayList<>();
        System.out.println("Hello from " + logo +
                "\nWhat can I do for you?\n");
        System.out.println("--------------------------");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            System.out.println("--------------------------");
            wizzer.executeCommand(command);
            System.out.println("--------------------------");
            command = scanner.nextLine();
        }
        scanner.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void executeCommand(String command) {
        String[] separateCommand = command.split(" ");
        System.out.println("--------------------------");
        System.out.println("--------------------------");
        if (command.equals("list")) {
            System.out.println("Task List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + tasks.get(i).toString());
            }
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            int taskNumber = Integer.parseInt(separateCommand[1]);
            if (command.startsWith("mark")) {
                tasks.get(taskNumber - 1).markAsDone();
                System.out.println(" Marked as done: ");
            } else if (command.startsWith("unmark")) {
                tasks.get(taskNumber - 1).markAsUndone();
                System.out.println(" Marked as not done yet: ");
            }
            System.out.println("   " + tasks.get(taskNumber - 1).toString());
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            if (command.startsWith("todo")) {
                String description = command.substring(5);
                tasks.add(new ToDo(description));
            } else if (command.startsWith("deadline")) {
                String[] parts = command.split("/by");
                String description = parts[0].substring(9).trim();
                String byID = parts[1].trim();
                tasks.add(new Deadline(description, byID));
            } else if (command.startsWith("event")) {
                String[] parts = command.split("/from");
                String description = parts[0].substring(6).trim();
                String[] timeParts = parts[1].split("/to");
                String start = timeParts[0].trim();
                String end = timeParts[1].trim();
                tasks.add(new Event(description, start, end));
            }
            System.out.println(" I've added this task:" + "\n" + "   " + tasks.get(tasks.size() - 1).toString()
                    + "\n" + " Now you have " + tasks.size() + " tasks in the list.");
        } else {
            Task currTask = new Task(separateCommand[0]);
            tasks.add(currTask);
            System.out.println("Added: " + currTask);
        }
    }
}
