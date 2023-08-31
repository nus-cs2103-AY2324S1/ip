import java.util.Scanner;

import static messages.Messages.MESSAGE_WELCOME;
import static messages.Messages.MESSAGE_EXIT;

public class Ui {

    private static final String LINE_DIVIDER = "────────────────────────────────────";

    public void printWelcomeMessage(String version) {
        printToUser(version);
        printToUser(MESSAGE_WELCOME);
    }

    public void printExitMessage() {
        printToUser(MESSAGE_EXIT);
    }

    public void printToUser(String text) {
        System.out.println(LINE_DIVIDER);
        System.out.println(text);
        System.out.println(LINE_DIVIDER);
    }

    public void runLoop(TaskList tasks) {
        Scanner scanner = new Scanner(System.in);

        String userInput;

        while (!(userInput = scanner.nextLine()).equals("bye")) {
            tasks.inputHandler(userInput);
        }

        scanner.close();
    }
}
