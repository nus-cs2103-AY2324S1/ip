package duke;

import java.util.Scanner;

import duke.exceptions.IncompleteDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.commands.Command;

/**
 * Main entry point for the textbot.
 */
public class Duke {
    public static void main(String[] args) {

        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/duke.txt");
        storage.loadTaskFile(taskList);

        System.out.println(Ui.opening);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            try {
                Command cmd = Parser.cmdFromString(input);
                cmd.execute(taskList, storage);
            } catch (UnknownCommandException | IncompleteDescriptionException ex) {
                System.out.println(ex);
            }
        }
    }
}
