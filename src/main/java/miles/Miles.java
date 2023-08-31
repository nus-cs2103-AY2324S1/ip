package miles;

import java.util.Scanner;

import miles.command.Command;

/**
 * Represents our chat bot, Miles.
 */
public class Miles {
    private static String filePath = "../../../data/miles.txt";
    private static String directoryPath = "../../../data";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Miles.
     * 
     * @param filePath The path to the file where the tasks are stored.
     */
    public Miles(String filePath) {
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
        Parser parser = new Parser();

        while (!shouldExit) {
            String input = scanner.nextLine();
            try {
                Command c = parser.parse(input);
                c.execute(this.taskList, this.ui, this.storage);
                shouldExit = c.isExit();
            } catch (MilesException e) {
                this.ui.printErrorMsg(e.getMessage());
                continue;
            }
        }

        scanner.close();
    }
    
    public static void main(String[] args) {
        new Miles(filePath).run();
    }
}
