package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * class where main is run
 */
public class Duke implements Serializable {
    protected static String indent = "   ";
    protected static String dukeFilePath = "data/duke.txt";
    protected static String tempFilePath = "data/temp.txt";
    private static Storage storage;
    private static TaskList tasks;
    private Ui ui;

    /**
     * constructor to initialise the Ui, Storage and TaskList objects
     * @param filePath the relative path to the file that acts as a temporary storage for the ArrayList(Task) object
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            Ui.printWithIndent("Hi! You do not have any tasks at the moment");
            createTxtFile();
            tasks = new TaskList();
        }
    }

    /**
     * The exception caught above is likely the IOException thrown at duke.Storage.java line 11. Catching
     * this exception means that duke.TaskList is empty (I think), which means that the duke.txt file might
     * not exist yet, so it is created in this function
     */
    public static void createTxtFile() {
        try {
            FileWriter fw = new FileWriter(dukeFilePath);
            fw.close();
        } catch (IOException e) {
            System.out.println("shag");
        }
    }
    /**
     * Runs the program
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = Parser.isExit();
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
                String userInput = ui.readCommand(scanner);
                ui.showLine();
                Parser.parse(userInput, tasks, storage);
                isExit = Parser.isExit();
                if (isExit) {
                    ui.showExit();
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke(tempFilePath).run();
    }
}
