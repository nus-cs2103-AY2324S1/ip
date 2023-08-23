import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static int id = 1;
    private static ArrayList<Task> taskList = new ArrayList<>(); //universal task list in memory

    public static void main(String[] args) {
        String logo = " __          _        \n"
                + "| |     _   _| | _____ \n"
                + "| |    | | | | / / _ \\\n"
                + "| |___ | |_| |   <  __/\n"
                + "|____/ \\__,__|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            Task newTask = new Task(input);

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                int id = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : taskList) {
                    System.out.println(String.valueOf(id) + ". " + task);
                    id++;
                }
                continue;
            }

            String[] parts = input.split(" ");
            if (parts.length >= 2) {
                String cmd = parts[0];
                int taskID = Integer.parseInt(parts[1]) - 1;
                Task taskChanged = taskList.get(taskID);
                if (cmd.equals("mark")) {
                    taskChanged.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + taskChanged);
                } else {
                    taskChanged.markUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + taskChanged);
                }
                continue;
            }

            taskList.add(newTask);
            System.out.println("added: " + input);

        }
        scanner.close();
    }
}
