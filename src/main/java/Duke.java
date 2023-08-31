import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import tasks.Task;

public class Duke {

    private static Storage storage;
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        storage = new Storage();

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

        String userInput;

        TaskList taskList = new TaskList(storage.load());

        while (!(userInput = scanner.nextLine()).equals("bye")) {
            System.out.println("────────────────────────────────────\n");
            taskList.inputHandler(userInput);

            System.out.println("────────────────────────────────────");
        }

        storage.save(taskList.getTaskList());

        System.out.println(exitMessage);
        scanner.close();
    }
}
