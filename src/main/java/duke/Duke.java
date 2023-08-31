package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private Parser parser;

    /**
     * Constructor for Duke.
     *
     * @param filePath Path of file that will be used as storage.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new Tasklist(storage.getTasks());
    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        Ui.greet();
        Scanner sc = new Scanner(System.in);
        String currStr = sc.nextLine();

        while (!currStr.equals("bye")) {
            Parser.parseResponse(currStr, tasks);
            currStr = sc.nextLine();
        }

        sc.close();
        Ui.goodbye();
        storage.updateTasks(tasks.getTasks());
    }

    public static void main(String[] args) {
         new Duke("data/list.txt").start();
    }
}
