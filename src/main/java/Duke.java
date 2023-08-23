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
                                   tasks.get(number - 1));
            } else if (userInput.startsWith("unmark")) {

                // retrieve the index input
                int index = userInput.indexOf(" ");
                String numStr = userInput.substring(index + 1);
                int number = Integer.parseInt(numStr);

                tasks.get(number - 1).setIsNotDone();

                System.out.println("Ok... Guess you're not actually done with this: \n" +
                        tasks.get(number - 1));
            } else {
                Task task = new Task(userInput);
                tasks.add(task);
                System.out.println("added: " + userInput + "\n");
            }
            System.out.println("────────────────────────────────────");



            userInput = scanner.nextLine();
        }

        System.out.println(exitMessage);
    }
}
