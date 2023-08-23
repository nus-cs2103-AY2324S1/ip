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
        ArrayList<String> tasks = new ArrayList<>(0);

        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                System.out.println("──────────────────────────────────── \n");
                if (tasks.size() == 0) {
                    System.out.println("There's nothing in your list /ᐠ｡ꞈ｡ᐟ\\ ");
                }

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i) + "\n");
                }
                System.out.println("────────────────────────────────────");
            } else {
                tasks.add(userInput);
                System.out.println("──────────────────────────────────── \n");
                System.out.println("added: " + userInput + "\n");
                System.out.println("────────────────────────────────────");
            }



            userInput = scanner.nextLine();
        }

        System.out.println(exitMessage);
    }
}
