import Commands.Command;
import Utilities.*;
import Utilities.Exceptions.IncompleteDescriptionException;
import Utilities.Exceptions.UnknownCommandException;

import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {

        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/duke.txt");

        Scanner sc = new Scanner(System.in);
        storage.loadTaskFile(taskList);
        System.out.println(Ui.opening);

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
