import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String botName = "Dude";
        System.out.println("Hello! I'm " + botName);
        System.out.println("What can I do for you?");

        //Add the ability to store whatever text entered by the user and display them back to the user when requested.
        ArrayList<Task> userTasks = new ArrayList<Task>();

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            if (input.equals("list")) {
                for (int i = 0; i < userTasks.size(); i++) {
                    System.out.println(i + 1 + ". [" + userTasks.get(i).getStatusIcon() + "] " + userTasks.get(i).getDescription());
                }
                continue;
            }
            // If input has mark followed by an integer, mark the task[i-1] as done.
            if (input.startsWith("mark")) {
                int TaskID = Integer.parseInt(input.substring(5)) - 1;
                userTasks.get(TaskID).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(userTasks.get(TaskID).toString());
            }
            // If input has delete followed by an integer, delete the task[i-1].
            else if (input.startsWith("unmark")) {
                int TaskID = Integer.parseInt(input.substring(7)) - 1;
                userTasks.get(TaskID).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(userTasks.get(TaskID).toString());
            }
            else { //input as new task
                Task task = new Task(input);
                userTasks.add(task);
                System.out.println("added: " + input);
            }

        } while (!input.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
