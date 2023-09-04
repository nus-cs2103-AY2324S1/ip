package duke.ui;
import duke.command.Command;
import duke.exception.KoraException;
import duke.parser.Parser;
import duke.task.TaskList;

import java.util.Scanner;
public class UI {

    private static Scanner scanner;
    public UI() {
        scanner = new Scanner(System.in);
    }

    public void closeScanner() {
        scanner.close();
    }

    public String getUserInput() {
        while (true) {
            if (scanner.hasNextLine()) {
                return scanner.nextLine();
            }
        }
    }

    public void greet() {
        String logo = "  _   _   _\n" +
                " / \\ / \\ / \\\n" +
                "( 안 | 녕 )\n" +
                " \\_/ \\_/ \\_/\n";
        System.out.println(logo + "Hello, I am your chatbot Kora!\nHow can I help you today?");
        System.out.println("------------------------------");
    }
}
