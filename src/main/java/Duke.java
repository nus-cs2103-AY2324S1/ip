import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String botName = "Dude";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        //Add the ability to store whatever text entered by the user and display them back to the user when requested.
        TaskManager userTasks = new TaskManager();

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("####################");
            input = scanner.nextLine();
            if (input.equals("bye")) {
                continue;
            }
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                System.out.println(userTasks.toString());
                continue;
            }
            // If input has mark followed by an integer, mark the task[i-1] as done.
            if (input.startsWith("mark")) {
                try {
                    int TaskID = Integer.parseInt(input.substring(5)) - 1;
                    userTasks.get(TaskID).markAsDone();
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please ensure task exists.");
                }
            }
            // If input has delete followed by an integer, delete the task[i-1].
            else if (input.startsWith("unmark")) {
                try {
                    int TaskID = Integer.parseInt(input.substring(7)) - 1;
                    userTasks.get(TaskID).markAsUndone();
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid task number.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please ensure task exists.");
                }
            }

            // New tasks are added to the list.
            else {
                try {
                    Task task = Task.createTask(input);
                    userTasks.add(task);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!input.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
