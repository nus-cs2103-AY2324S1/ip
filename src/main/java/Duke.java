import java.util.Scanner;

import duke.helper.DukeException;
import duke.helper.Parser;
import duke.helper.Ui;
import duke.storage.Storage;
import duke.storage.TaskList;

public class Duke {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String FILEPATH = "./data/tasks.txt";

    private TaskList taskList;
    
    public Duke(String path) {
        Storage.setPath(path);
        try {
            taskList = new TaskList(Storage.load());
        } catch (DukeException e) {
            Ui.print("Unable to load tasks");
            taskList = new TaskList();
        } finally {
            Parser.setTaskList(taskList);
        }
    }
    public static void main(String[] args) throws DukeException{   
        new Duke("data/tasks.txt");
        Ui.greet();
        String input = SCANNER.nextLine();

        while(!input.equals("bye")) {
            try {
                Parser.parse(input);
            } catch (DukeException e) {
                Ui.print(e.toString());
            } finally {
                input = SCANNER.nextLine();
            }
        }
        Ui.exit();
        SCANNER.close();
    }
}
