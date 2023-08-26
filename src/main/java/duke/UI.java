package duke;

import duke.Parser;
import duke.TaskList;

import java.util.Scanner;

public class UI {
    String stuff;
    Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
        stuff = scanner.nextLine();
    }

    public void run(TaskList items, Storage load, Parser parser) {
        while (!stuff.equalsIgnoreCase("bye")) {
            items = parser.parse(stuff,items);
            load.saveDataToFile(items.toArray());
            stuff = scanner.nextLine();
        }
    }
}
