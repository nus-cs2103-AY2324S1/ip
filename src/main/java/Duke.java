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
            input = scanner.nextLine();
            if (input.equals("list")) {
                for (int i = 0; i < userTasks.size(); i++) {
                    System.out.println(userTasks.get(i).toString());
                }
                continue;
            }
            // If input has mark followed by an integer, mark the task[i-1] as done.
            if (input.startsWith("mark")) {
                int TaskID = Integer.parseInt(input.substring(5)) - 1;
                userTasks.get(TaskID).markAsDone();
            }
            // If input has delete followed by an integer, delete the task[i-1].
            else if (input.startsWith("unmark")) {
                int TaskID = Integer.parseInt(input.substring(7)) - 1;
                userTasks.get(TaskID).markAsUndone();
            }

            // If input has todo, followed by the task, add the task as new Todo.
            else if (input.startsWith("todo")) {
                Task task = new Todo (input.substring(5));
                userTasks.add(task);
            }
            // If input has deadline, followed by the task and the deadline, add the task as new Deadline.
            else if (input.startsWith("deadline")) {
                String[] inputSplit = input.split(" /by ");
                Task task = new Deadline(inputSplit[0].substring(9), inputSplit[1]);
                userTasks.add(task);
            }
            // If input has event, followed by event name and start and end dates, add task as new Event.
            else if (input.startsWith("event")) {
                String[] inputSplit = input.split(" /");
                Task task = new Event(inputSplit[0].substring(6), inputSplit[1].substring(5), inputSplit[2].substring(3));
                userTasks.add(task);
            }
            else if (!input.equals("bye")){ //input as new task
                Task task = new Task(input);
                userTasks.add(task);
                System.out.println("added: " + input);
            }

        } while (!input.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}
