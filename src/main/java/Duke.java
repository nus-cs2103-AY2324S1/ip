import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> taskList = new ArrayList<>();


        // Greeting message
        System.out.println("Hello! I'm Sivraj");
        System.out.println("What can I do for you?");

        while (true) {
            String echo = scanner.nextLine();
            String dashLine = "----------------------------------------";

            try {

                if (echo.equals("bye")) {
                    // Farewell message
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (echo.equals("list")) {
                    System.out.println(dashLine);
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("     " + (i + 1) + "." + taskList.get(i));
                    }
                    System.out.println(dashLine);
                } else if (echo.startsWith("todo")) {
                    if (echo.length() <= 5) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    String description = echo.substring(5).trim();
                    taskList.add(new ToDo(description, 'T'));

                        System.out.println(dashLine);
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("         " + taskList.get(taskList.size() - 1));
                        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println(dashLine);

                } else if (echo.startsWith("deadline")) {
                    int byIndex = echo.indexOf("/by");
                    String description = echo.substring(9, byIndex).trim();
                    String by = echo.substring(byIndex + 3).trim();
                    taskList.add(new Deadline(description, by, 'D'));
                    System.out.println(dashLine);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + taskList.get(taskList.size() - 1));
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(dashLine);
                } else if (echo.startsWith("event")) {
                    int fromIndex = echo.indexOf("/from");
                    int toIndex = echo.indexOf("/to");
                    String description = echo.substring(6, fromIndex).trim();
                    String from = echo.substring(fromIndex + 5, toIndex).trim();
                    String to = echo.substring(toIndex + 3).trim();
                    taskList.add(new Event(description, from, to, 'E'));
                    System.out.println(dashLine);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + taskList.get(taskList.size() - 1));
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(dashLine);
                } else if (echo.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(echo.substring(5).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        taskList.get(taskIndex).markAsDone();
                        System.out.println(dashLine);
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("       " + taskList.get(taskIndex));
                        System.out.println(dashLine);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else if (echo.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(echo.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        taskList.get(taskIndex).markAsNotDone();
                        System.out.println(dashLine);
                        System.out.println("     OK, I've marked this task as not done yet:");
                        System.out.println("       " + taskList.get(taskIndex));
                        System.out.println(dashLine);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else if (echo.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(echo.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        Task removedTask = taskList.remove(taskIndex);
                        System.out.println(dashLine);
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + removedTask);
                        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println(dashLine);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(dashLine);
                System.out.println("    OOPS " + e.getMessage());
                System.out.println(dashLine);
            }
        }

        scanner.close();

    }
}
