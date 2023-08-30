package duke;

import java.util.Scanner;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * duke.Duke class that encapsulates a personal assistant chatbot.
 *
 * @author Pearlynn
 */

public class Duke {
    private static boolean isExit = false;
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;

    /**
     * Constructor for duke.Duke class.
     *
     * @param pathname The pathname of the file.
     */
    public Duke(String pathname) {
        Duke.ui = new Ui();
        Duke.ui.showWelcome();
        Duke.storage = new Storage(pathname);
        Duke.taskList = new TaskList(Duke.storage.loadData());
    }

    /**
     * Sets the exit status of the chatbot.
     *
     * @param isExit The boolean value.
     */
    public static void setExit(boolean isExit) {
        Duke.isExit = isExit;
    }

    /**
     * Gets the storage.
     *
     * @return The storage.
     */
    public static Storage getStorage() {
        return Duke.storage;
    }

    public static TaskList getTaskList() {
        return Duke.taskList;
    }

    /**
     * Gets the UI.
     *
     * @return The UI.
     */
    public static Ui getUi() {
        return Duke.ui;
    }

    private void run() {
        //duke.Duke.ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (!Duke.isExit) {
            String command = sc.nextLine();
            Parser.parse(command, Duke.taskList);
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
