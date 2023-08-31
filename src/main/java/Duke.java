import java.util.Scanner;

import exceptions.InvalidParametersException;
import exceptions.MissingDescriptionException;
import exceptions.UnknownCommandException;

public class Duke {
    public static void main(String[] args) {
        TaskStorage taskStorage = new TaskStorage();

        Messages.opener();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Commands command = Commands.parseCommand(input);
                switch (command) {
                case LIST:
                    taskStorage.printList();
                    break;
                case MARK:
                    taskStorage.markAsDone(input);
                    break;
                case UNMARK:
                    taskStorage.markAsUndone(input);
                    break;
                case TODO:
                    taskStorage.addTodo(input);
                    break;
                case DEADLINE:
                    taskStorage.addDeadline(input);
                    break;
                case EVENT:
                    taskStorage.addEvent(input);
                    break;
                case DELETE:
                    taskStorage.deleteTask(input);
                default:
                    break;
                }
            } catch (UnknownCommandException | MissingDescriptionException | InvalidParametersException e) {
                Messages.printInLine(e.getMessage());
            }
            input = sc.nextLine();
        }
        sc.close();
        Messages.printWithTab("Bye. Hope to see you again soon!");
    }
}
