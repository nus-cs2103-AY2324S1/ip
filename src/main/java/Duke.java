
import java.util.Scanner;

import duke.Messages;
import duke.Parser;
import duke.TaskListStorage;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidIndexException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.UnknownCommandException;

public class Duke {
    public static void main(String[] args) {
        TaskListStorage tasklistStorage = new TaskListStorage();
        Parser parser = new Parser();

        Messages.opener();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                parser.dispatch(input).execute(tasklistStorage);
            } catch (UnknownCommandException | MissingDescriptionException | IncorrectCommandFormatException | InvalidIndexException | InvalidTimeFormatException e) {
                Messages.printInLine(e.getMessage());
            }
            input = sc.nextLine();
        }
        sc.close();
        Messages.printWithTab("Bye. Hope to see you again soon!");
    }
}
