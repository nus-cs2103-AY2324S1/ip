import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class Moss {
    static ArrayList<Task> things = new ArrayList<>();
    public static void main(String[] args) {

        String greet = "____________________________________________________________\n"
                + "Hello! I'm Moss \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while (!message.equals("bye")) {
            if (message.equals("list")){
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < things.size(); i++) {
                    System.out.println(i+1 + "." + things.get(i).toString());
                }
                System.out.println("____________________________________________________________");
            }
            else if (message.startsWith("mark")) {
                String indexSubstring = message.substring(5);
                int index = Integer.parseInt(indexSubstring) - 1;
                things.get(index).markDone();
            }
            else if (message.startsWith("unmark")) {
                String indexSubstring = message.substring(7);
                int index = Integer.parseInt(indexSubstring) - 1;
                things.get(index).markUndone();
            }
            else {
                add(message);
            }
            message = sc.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Adds a new task with the given description to the list of tasks.
     *
     * @param message The description of the task to be added.
     */
    public static void add(String message) {
        try {
            if (message.startsWith("todo")) {
                // check if the command is valid otherwise throw errors
                if (message.length() <= 5) {
                    throw new MossException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                ToDo task = new ToDo(message.substring(5));
                things.add(task);
            }
            else if (message.startsWith("deadline")) {
                // Find the position of "/by" in the input
                if (message.length() <= 9) {
                    throw new MossException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                int byIndex = message.indexOf("/by");

                // Extract the substring after "/by"
                String deadlineInfo = message.substring(byIndex + 4).trim();

                // Split the deadlineInfo by space to get individual parts
                String[] parts = deadlineInfo.split(" ");

                // The day is the last part of the parts array
                String day = parts[parts.length - 1];
                // Extract the substring before "/by"
                String taskDescription = message.substring(9, byIndex).trim();

                Deadline task = new Deadline(taskDescription, day);
                things.add(task);
            }
            else if (message.startsWith("event")) {
                if (message.length() <= 6) {
                    throw new MossException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                int byIndex = message.indexOf("/from");

                int fromIndex = message.indexOf("/from");
                int toIndex = message.indexOf("/to");

                // Extract the substring between "/from" and "/to" and behind "to"
                String from = message.substring(fromIndex + 5, toIndex).trim();
                String to = message.substring(toIndex + 3).trim();

                String taskDescription = message.substring(6, byIndex).trim();

                Event task = new Event(taskDescription, from, to);
                things.add(task);
            }
            // check if the command is valid otherwise throw errors
            else {
                throw new MossException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task: ");
            System.out.println(things.get(things.size() - 1).toString());
            System.out.println("Now you have " + things.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (MossException e) {
            System.out.println("____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

}
