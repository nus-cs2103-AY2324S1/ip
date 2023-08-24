import tasks.*;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = "  OOOO                         OOOO\n" +
                " O    O     w           w     O    O\n" +
                " O    O      w   w w   w      O    O\n" +
                " O    O       w w   w w       O    O\n" +
                "  OOOO         w     w         OOOO";

        String welcomeMessage = "────────────────────────────────────\n" +
                "Hello >u<! I'm OwO_bot\n\n" +
                logo + "\n\n" +
                "How can I help ♥w♥ ?\n" +
                "────────────────────────────────────";

        String exitMessage = "────────────────────────────────────\n" +
                "Bye! Hope to see you again soon!\n" +
                "────────────────────────────────────";

        System.out.println(welcomeMessage);

        String userInput = scanner.nextLine();
        TaskList taskList = new TaskList();

        while (!userInput.equals("bye")) {
            System.out.println("────────────────────────────────────\n");
            taskList.inputHandler(userInput);
            System.out.println("────────────────────────────────────");


            userInput = scanner.nextLine();
        }

        System.out.println(exitMessage);
    }

}
