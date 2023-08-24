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
        } else {
            Task currTask = new Task(separateCommand[0]);
            tasks.add(currTask);
            System.out.println("Added: " + currTask);
        }
    }
}
