package duke;

import duke.Parser;
import duke.TaskList;

import java.util.Scanner;

/**
 * The UI class to handle user interactions
 */
public class UI {
    String stuff;
    Scanner scanner;

    /**
     * Constructor to init the class
     */
    public UI() {
        this.scanner = new Scanner(System.in);
        stuff = scanner.nextLine();
    }

    /**
     * Run the UI
     * @param items tasklist to use
     * @param load load to use
     * @param parser parser to use
     */
    public void run(TaskList items, Storage load, Parser parser) {
        while (!stuff.equalsIgnoreCase("bye")) {
            items = parser.parse(stuff,items);
            load.saveDataToFile(items.toArray());
            stuff = scanner.nextLine();
        }
    }
}
