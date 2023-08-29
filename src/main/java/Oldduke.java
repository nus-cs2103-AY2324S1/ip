import java.util.ArrayList;
import java.util.Scanner;

public class Oldduke {
    public static void main(String[] args) {

        String greeting = "____________________________________________________________\n"
                + "Hello! I'm yourChatBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        ArrayList<Task> todolist = new ArrayList<Task>();

        while (scanner.hasNextLine()) {
            userInput = scanner.nextLine();
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    String todolistoutput = "";
                    for (int i = 0; i < todolist.size(); i++) {
                        todolistoutput += i + 1 + "."
                                + todolist.get(i) + "\n";
                    }
                    System.out.println("____________________________________________________________\n"
                            + todolistoutput
                            + "____________________________________________________________\n");

                } else if (userInput.equalsIgnoreCase("bye")) {
                    break;
                } else if (userInput.startsWith("mark")) {
                    int index = Integer.valueOf(userInput.split(" ", 0)[1]);
                    todolist.get(index - 1).markAsDone();
                    System.out.println("____________________________________________________________\n"
                            + "Nice! I've marked this task as done:\n"
                            + todolist.get(index - 1) + "\n"
                            + "____________________________________________________________\n");
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.valueOf(userInput.split(" ", 0)[1]);
                    todolist.get(index - 1).unmark();
                    System.out.println("____________________________________________________________\n"
                            + "OK, I've marked this task as not done yet:\n"
                            + todolist.get(index - 1) + "\n"
                            + "____________________________________________________________\n");
                } else {
                    Task task = null;
                    if (userInput.startsWith("todo", 0)) {
                        if (userInput.length() < 5) {
                            throw new IllegalArgumentException(
                                    "☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new ToDo(userInput.substring(5));
                    } else if (userInput.startsWith("deadline", 0)) {
                        if (userInput.length() < 9) {
                            throw new IllegalArgumentException(
                                    "☹ OOPS!!! The description of a deadline cannot be empty.");
                        } else if (!userInput.contains("/by")) {
                            throw new IllegalArgumentException(
                                    "☹ OOPS!!! The deadline date of a deadline cannot be empty.");
                        }
                        task = new Deadline(userInput.substring(9).split("/")[0],
                                userInput.substring(9).split("/")[1].substring(3));
                    } else if (userInput.startsWith("event", 0)) {
                        if (userInput.length() < 9) {
                            throw new IllegalArgumentException(
                                    "☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        task = new Event(userInput.substring(6).split("/")[0],
                                userInput.substring(6).split("/")[1].substring(5),
                                userInput.substring(6).split("/")[2].substring(3));
                    } else if (userInput.startsWith("delete", 0)) {
                        int index = Integer.valueOf(userInput.split(" ", 0)[1]);
                        if (index > todolist.size()) {
                            throw new IllegalArgumentException("Index beyond size of ToDo List");
                        }
                        todolist.get(index - 1).remove();
                        System.out.println("____________________________________________________________\n"
                                + "Noted. I've removed this task:\n"
                                + todolist.get(index - 1) + "\n"
                                + "Now you have " + Task.getTotal() + " tasks in the list.\n"
                                + "____________________________________________________________\n");
                        todolist.remove(index - 1);
                        continue;
                    } else {
                        throw new IllegalArgumentException("Incorrect Input");
                    }
                    todolist.add(task);
                    System.out.println("____________________________________________________________\n"
                            + "Got it. I've added this task:\n"
                            + " " + task
                            + "\n"
                            + "Now you have " + Task.getTotal() + " tasks in the list.\n"
                            + "____________________________________________________________\n");

                }
            } catch (IllegalArgumentException e) {
                System.out.println("____________________________________________________________\n"
                        + e.getMessage() + "\n"
                        + "____________________________________________________________\n");
            }

        }

        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________");

    }
}