import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm yourChatBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greeting);

        try {
            // Future Inputs
            Scanner scanner = new Scanner(System.in);
            String userInput;
            ArrayList<Task> todolist = new ArrayList<Task>();

            while (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
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
                            + " [" + todolist.get(index - 1).getStatusIcon() + "] "
                            + todolist.get(index - 1) + "\n"
                            + "____________________________________________________________\n");
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.valueOf(userInput.split(" ", 0)[1]);
                    todolist.get(index - 1).unmark();
                    System.out.println("____________________________________________________________\n"
                            + "OK, I've marked this task as not done yet:\n"
                            + " [" + todolist.get(index - 1).getStatusIcon() + "] "
                            + todolist.get(index - 1) + "\n"
                            + "____________________________________________________________\n");
                } else {
                    Task task = null;
                    if (userInput.startsWith("todo", 0)) {
                        task = new ToDo(userInput.substring(5));
                    } else if (userInput.startsWith("deadline", 0)) {
                        task = new Deadline(userInput.substring(9).split("/")[0],
                                userInput.substring(9).split("/")[1].substring(3));
                    } else if (userInput.startsWith("event", 0)) {
                        task = new Event(userInput.substring(6).split("/")[0],
                                userInput.substring(6).split("/")[1].substring(5),
                                userInput.substring(6).split("/")[2].substring(3));
                    } else {
                        System.out.println("____________________________________________________________\n"
                                + "Incorrect Input\n"
                                + "____________________________________________________________\n");
                        continue;
                    }
                    todolist.add(task);
                    System.out.println("____________________________________________________________\n"
                            + "Got it. I've added this task:\n"
                            + " " + task
                            + "\n"
                            + "Now you have " + Task.getTotal() + " tasks in the list.\n"
                            + "____________________________________________________________\n");

                }

            }
        } finally {
            System.out.println("Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________");
        }
    }
}
