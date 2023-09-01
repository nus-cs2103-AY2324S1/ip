
import java.util.Scanner;

import duke.Messages;
import duke.TaskListStorage;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.IncorrectCommandFormatException;

public class Duke {
    public static void main(String[] args) {
        TaskListStorage tasklistStorage = new TaskListStorage();

        Messages.opener();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Commands command = Commands.parseCommand(input);
                switch (command) {
                case LIST:
                    tasklistStorage.printList();
                    break;
                case MARK:
                    tasklistStorage.markAsDone(input);
                    break;
                case UNMARK:
                    tasklistStorage.markAsUndone(input);
                    break;
                case TODO:
                    tasklistStorage.addTodo(input);
                    break;
                case DEADLINE:
                    tasklistStorage.addDeadline(input);
                    break;
                case EVENT:
                    tasklistStorage.addEvent(input);
                    break;
                case DELETE:
                    tasklistStorage.deleteTask(input);
                default:
                    break;
                }
            } catch (UnknownCommandException | MissingDescriptionException | IncorrectCommandFormatException e) {
                Messages.printInLine(e.getMessage());
            }
            input = sc.nextLine();
        }
        sc.close();
        Messages.printWithTab("Bye. Hope to see you again soon!");
    }
}
