import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList list = null;

        Storage storage = new Storage();
        list = new TaskList(storage.getFile());

        Parser parser = new Parser();

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        String input = scanner.nextLine();

        while (true) {
            try {
                Parser.Command command = parser.parse(input);
                if (command == Parser.Command.BYE) {
                    list.close();
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (command == Parser.Command.LIST) {
                    list.printList();
                } else {
                    list.executeCommand(command, input);
//                    list.addTask(command, input);
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
            input = scanner.nextLine();
        }
    }
}
