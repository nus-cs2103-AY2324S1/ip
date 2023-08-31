package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Tasklist tasks;
    private Parser parser;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new Tasklist(storage.getTasks());
    }

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
