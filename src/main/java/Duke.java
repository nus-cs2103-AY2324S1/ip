import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String HORIZONTAL_LINE = "____________________________________________________________\n";

        String entryMessage = HORIZONTAL_LINE
                + "Hello! I'm Chad \n"
                + "What can I do for you? \n"
                + HORIZONTAL_LINE;

        String exitMessage = HORIZONTAL_LINE
                + "Bye. Hope to see you again soon!\n"
                + HORIZONTAL_LINE;

        Scanner scanner = new Scanner(System.in);
        String input = "";
        ArrayList<Task> list = new ArrayList<>();

        System.out.println(entryMessage);
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (input.equals("list")) {
                System.out.print(HORIZONTAL_LINE);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "."+ list.get(i).toString());
                }
                System.out.println(HORIZONTAL_LINE);
            } else if (input.startsWith("mark")) {
                try {
                    Task task = list.get(Integer.valueOf(input.substring(5)) - 1);
                    task.markAsDone();

                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                    System.out.println(HORIZONTAL_LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("You have entered an invalid task number.");
                    System.out.println(HORIZONTAL_LINE);
                }
            } else if (input.startsWith("unmark")) {
                try {
                    Task task = list.get(Integer.valueOf(input.substring(7)) - 1);
                    task.markAsNotDone();

                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                    System.out.println(HORIZONTAL_LINE);
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("You have entered an invalid task number.");
                    System.out.println(HORIZONTAL_LINE);
                }
            } else if (input.startsWith("deadline")) {
                int byIndex = input.indexOf("/by");

                try {
                    String description = input.substring(9, byIndex - 1);
                    String by = input.substring(byIndex + 4);

                    Deadline deadline = new Deadline(description, by);
                    list.add(deadline);

                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("Oops! Invalid input for your Deadline task.");
                    System.out.println("Valid Format: deadline (description) /by (date-time)");
                    System.out.println(HORIZONTAL_LINE);
                }
            } else if (input.startsWith("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");

                try {
                    String description = input.substring(6, fromIndex - 1);
                    String from = input.substring(fromIndex + 6, toIndex - 1);
                    String to = input.substring(toIndex + 4);

                    Event event = new Event(description, from, to);
                    list.add(event);

                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("Oops! Invalid input for your Event task.");
                    System.out.println("Valid Format: event (description) /from (date-time) /to (date-time)");
                    System.out.println(HORIZONTAL_LINE);
                }
            } else if (input.startsWith("todo")) {
                try {
                    ToDo todo = new ToDo(input.substring(5));
                    list.add(todo);

                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println(HORIZONTAL_LINE);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.print(HORIZONTAL_LINE);
                    System.out.println("Oops! The description of a todo cannot be empty.");
                    System.out.println("Valid Format: todo (description)");
                    System.out.println(HORIZONTAL_LINE);
                }
            } else if (input.equals("bye")) {
                System.out.println(exitMessage);
                break;
            } else {
                System.out.print(HORIZONTAL_LINE);
                System.out.println("Oops! I'm sorry, but I don't know what that means :-(");
                System.out.println(HORIZONTAL_LINE);
            }
        }
        scanner.close();
    }
}
