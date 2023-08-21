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
            ArrayList<String> todolist = new ArrayList<String>();

            while (true) {
                userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("list")) {
                    String todolistoutput = "";
                    for (int i = 0; i < todolist.size(); i++) {
                        todolistoutput += i + 1 + ". " + todolist.get(i) + "\n";
                    }
                    System.out.println("____________________________________________________________\n"
                            + todolistoutput
                            + "____________________________________________________________\n");

                } else if (userInput.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    todolist.add(userInput);
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
