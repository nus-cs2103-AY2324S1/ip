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

            while (true) {
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("list")) {
                    String todolistoutput = "";
                    for (int i = 0; i < todolist.size(); i++) {
                        todolistoutput += i + 1 + "." + "[" + todolist.get(i).getStatusIcon() + "]"
                                + todolist.get(i).getString() + "\n";
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
                            + todolist.get(index - 1).getString() + "\n"
                            + "____________________________________________________________\n");
                } else if (userInput.startsWith("unmark")) {
                    int index = Integer.valueOf(userInput.split(" ", 0)[1]);
                    todolist.get(index - 1).unmark();
                    System.out.println("____________________________________________________________\n"
                            + "OK, I've marked this task as not done yet:\n"
                            + " [" + todolist.get(index - 1).getStatusIcon() + "] "
                            + todolist.get(index - 1).getString() + "\n"
                            + "____________________________________________________________\n");
                } else {
                    Task task = new Task(userInput);
                    todolist.add(task);
                    System.out.println("____________________________________________________________\n"
                            + "Added: "
                            + userInput
                            + "\n"
                            + "____________________________________________________________\n");

                }

            }
        } finally {
            System.out.println("Bye. Hope to see you again soon!\n"
                    + "____________________________________________________________");
        }
    }
}
