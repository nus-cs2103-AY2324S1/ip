package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Duke class represents a chatbot that generates a list of tasks based on user input.
 * These tasks can include deadlines, events, and ToDos. The user input is parsed to extract
 * dates and times for events and deadlines.
 */
public class Duke {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Ui ui = new Ui();
        ui.printHello();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Parser parser = new Parser();
        TaskList tasks = storage.loadTasks("src/data/Duke.txt");
        while (!str.equals("bye")) {
            parser.chat(str, tasks);
            str = scanner.nextLine();
        }
        try {
            storage.saveTasks("src/data/Duke.txt", tasks);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
        ui.printBye();
    }
}

