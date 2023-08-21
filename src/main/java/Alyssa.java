import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the main program.
 */
public class Alyssa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String logo = " ___  __    __   __  ____   ____    ___\n"
                    + "|   | | |   \\ \\ / / |  __| |  __|  |   |\n"
                    + "|   | | |    \\   /   \\ \\    \\ \\    |   |\n"
                    + "|___| | |     | |     \\ \\    \\ \\   |___|\n"
                    + "|   | | |___  | |     _\\ \\   _\\ \\  |   |\n"
                    + "|   | |_____| |_|    |____| |____| |   |\n";
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Alyssa!");
        System.out.println("What can I do for you?");
        System.out.println(line);
        List<Task> taskList = new ArrayList<>();
        while (true) {
            String nextInput = sc.nextLine();
            if (nextInput.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            } else if (nextInput.equals("list")) {
                int counter = 1;
                System.out.println(line);
                System.out.println("Here are the tasks in your list:");
                for (Task task : taskList) {
                    System.out.println(counter + "." + task.toString());
                    counter++;
                }
                System.out.println(line);
            } else if (nextInput.startsWith("mark")) {
                String[] argumentArray = nextInput.split(" ");
                try {
                    int index = Integer.valueOf(argumentArray[1]);
                    if (index < 1 || index > taskList.size()) {
                        throw new Exception("Invalid index");
                    }
                    Task task = taskList.get(index - 1);
                    task.markAsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task.toString());
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a valid task number.");
                    System.out.println(line);
                }
            } else if (nextInput.startsWith("unmark")) {
                String[] argumentArray = nextInput.split(" ");
                try {
                    int index = Integer.valueOf(argumentArray[1]);
                    if (index < 1 || index > taskList.size()) {
                        throw new Exception("Invalid index");
                    }
                    Task task = taskList.get(index - 1);
                    task.markAsUndone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task.toString());
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Please enter a valid task number.");
                    System.out.println(line);
                }
            } else if (nextInput.startsWith("todo")) {
                try {
                    String desc = nextInput.substring(5);
                    Task newTodo = new Todo(desc);
                    taskList.add(newTodo);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(line);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(line);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(line);
                }
            } else if (nextInput.startsWith("deadline")) {
                try {
                    String[] parsed = nextInput.split(" /by ");
                    String desc = parsed[0].substring(9);
                    String by = parsed[1];
                    Task newDeadline = new Deadline(desc, by);
                    taskList.add(newDeadline);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Invalid deadline");
                    System.out.println(line);
                }
            } else if (nextInput.startsWith("event")) {
                try {
                    String[] parsed = nextInput.split(" /from | /to ");
                    String desc = parsed[0].substring(6);
                    String from = parsed[1];
                    String to = parsed[2];
                    Task newEvent = new Event(desc, from, to);
                    taskList.add(newEvent);
                    System.out.println(line);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent.toString());
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(line);
                    System.out.println("Invalid event");
                    System.out.println(line);
                }
            } else {
                System.out.println(line);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }
        }
    }
}
