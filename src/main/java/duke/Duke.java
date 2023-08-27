package duke;

import java.util.Scanner;

/**
 * Represents our chat bot, Miles.
 */
public class Duke {
    private static String filePath = "../../../data/miles.txt";
    private static String directoryPath = "../../../data";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, directoryPath);
        this.taskList = this.storage.loadFile();
    }

    /**
     * Runs the program.
     */
    public void run() {
        this.ui.greet();
        boolean shouldExit =  false;
        Scanner scanner  = new Scanner(System.in);
        Parser parser = new Parser(this.storage, this.taskList);

        while (!shouldExit) {
            String input = scanner.nextLine();
            shouldExit = parser.parse(input);
        }

        scanner.close();
        this.ui.exit();
    }
    
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
