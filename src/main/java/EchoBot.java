import java.util.ArrayList;
import java.util.Scanner;

public class EchoBot {
    public static int extractTaskNum(String userInput, String command) {
        String taskNumberStr = userInput.substring(command.length()).trim();
        return Integer.parseInt(taskNumberStr);
    }
    public static String extractTaskDesc(String userInput, String command) {
        return userInput.substring(command.length()).trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "   _____________________________________________________________\n";

        //ArrayList to store the tasks
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(horizontalLine + "    Hello! I'm EchoBot\n" + logo);
        System.out.println("    What can I do for you?\n" + horizontalLine);

        while(true) {
            // Read the user input
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(horizontalLine + "    Bye. Hope to see you again soon!\n" + horizontalLine);
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine + "    Here are the tasks in your list:");

                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.println("    " + (i + 1) + "." + task);
                }

                System.out.println(horizontalLine);
            } else if (userInput.startsWith("todo")) {
                try {
                    String taskDescription = extractTaskDesc(userInput, "todo");
                    if (taskDescription.isEmpty()) {
                        throw new DukeException(horizontalLine + "    ☹ OOPS!!! The description of a todo cannot be empty."
                            + "\n"+ horizontalLine);
                    }

                    Task newTodo = new Todo(taskDescription);
                    tasks.add(newTodo);

                    System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + newTodo);
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + horizontalLine);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("deadline")) {
                String taskDescription = extractTaskDesc(userInput, "deadline");
                int indexOfBy = taskDescription.indexOf("/by");
                String deadlineDescription = taskDescription.substring(0, indexOfBy).trim();
                String by = taskDescription.substring(indexOfBy + 3).trim();
                Task newDeadline = new Deadline(deadlineDescription, by);
                tasks.add(newDeadline);

                System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + newDeadline);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + horizontalLine);
            } else if (userInput.startsWith("event")) {
                String taskDescription = extractTaskDesc(userInput, "event");
                int indexOfFrom = taskDescription.indexOf("/from");
                int indexOfTo = taskDescription.indexOf("/to");
                String eventDescription = taskDescription.substring(0, indexOfFrom).trim();
                String from = taskDescription.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = taskDescription.substring(indexOfTo + 3).trim();
                Task newEvent = new Event(eventDescription, from, to);
                tasks.add(newEvent);

                System.out.println(horizontalLine + "    Got it. I've added this task:\n" + "     " + newEvent);
                System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + horizontalLine);
            } else if (userInput.startsWith("mark")) {
                int taskNum = extractTaskNum(userInput, "mark");

                if (taskNum >= 1 && taskNum <= tasks.size()) {
                    Task task = tasks.get(taskNum - 1);
                    task.mark();

                    System.out.println(horizontalLine + "    Nice! I've marked this task as done:");
                    System.out.print("      [" + task.getStatusIcon() + "] " + task.getDescription());

                    // Additional information
                    if (task instanceof Event) {
                        System.out.print(" (from: " + ((Event) task).from + " to: " + ((Event) task).to + ")");
                    } else if (task instanceof Deadline) {
                        System.out.print(" (by: " + ((Deadline) task).by + ")");
                    }

                    System.out.println("\n" + horizontalLine);
                }
            } else if (userInput.startsWith("unmark")) {
                int taskNum = extractTaskNum(userInput, "unmark");

                if (taskNum >= 1 && taskNum <= tasks.size()) {
                    Task task = tasks.get(taskNum - 1);
                    task.unmark();

                    System.out.println(horizontalLine + "    OK, I've marked this task as not done yet:");
                    System.out.print("      [" + task.getStatusIcon() + "] " + task.getDescription());

                    // Additional information
                    if (task instanceof Event) {
                        System.out.print(" (from: " + ((Event) task).from + " to: " + ((Event) task).to + ")");
                    } else if (task instanceof Deadline) {
                        System.out.print(" (by: " + ((Deadline) task).by + ")");
                    }

                    System.out.println("\n" + horizontalLine);
                }
            } else if (userInput.startsWith("delete")) {
                int taskNum = extractTaskNum(userInput, "delete");

                if (taskNum >= 1 && taskNum <= tasks.size()) {
                    Task deletedTask = tasks.remove(taskNum - 1);

                    System.out.println(horizontalLine + "    Noted. I've removed this task:\n" + "     " + deletedTask.toString());
                    System.out.println("    Now you have " + tasks.size() + " tasks in the list.\n" + horizontalLine);
                }
            } else {
                System.out.println(horizontalLine + "    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(horizontalLine);
            }
        }
    }
}
