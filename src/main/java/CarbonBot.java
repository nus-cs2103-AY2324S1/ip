import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarbonBot {
    private static String DIVIDER = "____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.println(DIVIDER);
        System.out.println("Hello! I'm CarbonBot");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        while(true) {
            String input = sc.nextLine();

            // Do not process if the input was empty
            if (input.isEmpty()) {
                continue;
            }

            String cmd = input.split(" ")[0];
            int taskIdx;

            System.out.println(DIVIDER);
            switch(cmd) {
                case "bye":
                    // Stops listening to user input if the command is "bye"
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(DIVIDER);
                    sc.close();
                    return;
                case "list":
                    // Lists all the commands saved in the arraylist
                    int idx = 1;
                    for(Task t : taskList) {
                        System.out.println(String.format("%d.%s", idx, t));
                        idx++;
                    }
                    break;
                case "mark":
                    updateTaskStatus(taskList, input, true);
                    break;
                case "unmark":
                    updateTaskStatus(taskList, input, false);
                    break;
                default:
                    // Adds the task to the list
                    Task task = new Task(input);
                    taskList.add(task);
                    System.out.println("added: " + input);
            }
            System.out.println(DIVIDER);
        }
    }

    private static void updateTaskStatus(List<Task> tasks, String input, boolean isDone) {
        // Check if the user has specified the index to be updated
        if (input.split(" ").length < 2) {
            System.out.println("Please provide the task index to be updated.");
            return;
        }

        try {
            int taskIdx = Integer.parseInt(input.split(" ")[1]);
            if (taskIdx > 0 && taskIdx <= tasks.size()) {
                // The -1 is because of the list 0-indexing
                Task t = tasks.get(taskIdx - 1);

                if (isDone) {
                    // Mark task as done
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    // Mark task as not done
                    t.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                System.out.println(t);
            } else {
                // Do not process the command if the index was out of bounds
                System.out.println("Index given was out-of-bounds");
            }
        } catch (NumberFormatException ex) {
            // Ensure the index is integer
            System.out.println("Please provide a valid integer index.");
        }

    }

}
