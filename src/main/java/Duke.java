import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = "  OOOO                         OOOO  \n" +
                      " O    O     w           w     O    O \n" +
                      " O    O      w   w w   w      O    O \n" +
                      " O    O       w w   w w       O    O \n" +
                      "  OOOO         w     w         OOOO  ";

        String welcomeMessage = "──────────────────────────────────── \n" +
                                "Hello >u<! I'm OwO_bot \n \n" +
                                logo + "\n \n" +
                                "How can I help ♥w♥ ? \n" +
                                "────────────────────────────────────";

        String exitMessage = "──────────────────────────────────── \n" +
                             "Bye! Hope to see you again soon! \n" +
                             "────────────────────────────────────";

        System.out.println(welcomeMessage);

        String userInput = scanner.nextLine();
        ArrayList<Task> tasks = new ArrayList<>(0);

        while (!userInput.equals("bye")) {
            System.out.println("──────────────────────────────────── \n");
            if (userInput.equals("list")) {

                System.out.println("Here are the tasks in your list: \n");

                if (tasks.size() == 0) {
                    System.out.println("There's nothing in your list /ᐠ｡ꞈ｡ᐟ\\ ");
                }

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i) + "\n");
                }

            } else if (userInput.startsWith("mark")) {

                // retrieve the index input
                int index = userInput.indexOf(" ");
                String numStr = userInput.substring(index + 1);
                int number = Integer.parseInt(numStr);
                System.out.println(number);
                tasks.get(number - 1).setIsDone();

                System.out.println("Yay! You have completed this task: \n" +
                                   tasks.get(number - 1) + "\n");

            } else if (userInput.startsWith("unmark")) {

                // retrieve the index input
                int index = userInput.indexOf(" ");
                String numStr = userInput.substring(index + 1);
                int number = Integer.parseInt(numStr);
                tasks.get(number - 1).setIsNotDone();

                System.out.println("Ok... Guess you're not actually done with this: \n" +
                        tasks.get(number - 1) + "\n");

            } else if (userInput.startsWith("todo")) {

                String description = userInput.substring(userInput.indexOf(" "));
                ToDo todo = new ToDo(description);
                tasks.add(todo);

                System.out.println("added new task: \n" + todo);
                System.out.println("you now have " + tasks.size() + " tasks in your list. " + "\n");

            } else if (userInput.startsWith("deadline")) {

                String[] parts = userInput.split("/");
                String description = parts[0].substring(userInput.indexOf(" "));
                String end = parts[1].trim();
                Deadline deadline = new Deadline(description, end);
                tasks.add(deadline);

                System.out.println("added Deadline task: \n" + deadline + "\n");
                System.out.println("you now have " + tasks.size() + " tasks in your list. " + "\n");

            } else if (userInput.startsWith("event")) {

                String[] parts = userInput.split("/");
                String description = parts[0].substring(userInput.indexOf(" "));
                String start = parts[1].trim();
                String end = parts[2].trim();
                Event event = new Event(description, start, end);
                tasks.add(event);

                System.out.println("added Event task: \n" + event + "\n");
                System.out.println("you now have " + tasks.size() + " tasks in your list. " + "\n");

            } else {
                System.out.println("invalid input: " + userInput + "\n");
            }
            System.out.println("────────────────────────────────────");



            userInput = scanner.nextLine();
        }

        System.out.println(exitMessage);
    }
}
