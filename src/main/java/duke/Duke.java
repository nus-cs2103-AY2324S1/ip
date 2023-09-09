package duke;

import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Duke class represents a chatbot that generates a list of tasks based on user input.
 * These tasks can include deadlines, events, and ToDos. The user input is parsed to extract
 * dates and times for events and deadlines.
 */

public class Duke{
    TaskList tasks = Storage.loadTasks("src/data/Duke.txt");
    public void dukeInteraction() {
        Ui.printHello();
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (!str.equals("bye")) {
            Parser.chat(str, tasks);
            str = scanner.nextLine();
        } try {
            Storage.saveTasks("src/data/Duke.txt", tasks);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        scanner.close();
        Ui.printBye();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.dukeInteraction();
    }
    public String getResponse(String input) {
        return Parser.chat(input, tasks);
    }
}

