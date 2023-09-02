package duke;

import java.util.Scanner;

/**
 * The UI class to handle user interactions
 */
public class UI {
    private String stuff;
    private Scanner scanner;

    /**
     * Constructor to init the class
     */
    public UI() {

    }

    /**
     * Run the UI
     * @param items tasklist to use
     * @param load load to use
     * @param parser parser to use
     */
    public void run(TaskList items, Storage load, Parser parser) {
        this.scanner = new Scanner(System.in);
        stuff = scanner.nextLine();
        while (!stuff.equalsIgnoreCase("bye")) {
            items = parser.parse(stuff, items);
            load.saveDataToFile(items.toArray());
            stuff = scanner.nextLine();
        }
    }

    /**
     * Run the UI
     * @param items tasklist to use
     * @param load load to use
     * @param parser parser to use
     */
    public String run1(String command, TaskList items, Storage load, Parser parser) {

        items = parser.parse(command, items);
        String reply = parser.get();
        load.saveDataToFile(items.toArray());
        return reply;

    }
}
