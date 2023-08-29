package duke;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.util.Scanner;

/**
 * duke.Duke class that encapsulates a personal assistant chatbot.
 *
 * @author Pearlynn
 */

public class Duke {
    public static boolean isExit = false;
    public static Ui ui;
    public static Storage storage;
    public static TaskList taskList;

    public Duke(String pathname) {
        Duke.ui = new Ui();
        Duke.ui.showWelcome();
        Duke.storage = new Storage(pathname);
        Duke.taskList = new TaskList(Duke.storage.loadData());
    }

    private void run() {
        //duke.Duke.ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while(!Duke.isExit) {
            String command = sc.nextLine();
            Parser.parse(command, Duke.taskList);
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
