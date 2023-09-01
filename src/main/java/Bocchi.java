import exceptions.BocchiException;
import parser.Parser;
import storage.BocchiLoader;
import storage.BocchiSaver;
import task.TaskList;
import ui.Ui;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for running the task manager
 */
public class Bocchi {
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes the program
     */
    public Bocchi() {
        this.ui = new Ui();
        this.parser = new Parser(this.ui);
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        new Bocchi().run();
    }

    /**
     * Runs the program
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        TaskList taskList;
        try {
            taskList = new BocchiLoader().loadTaskList();
            ui.loadSuccessful();
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
            ui.loadUnsuccessful();
        }
        ui.greet();
        String message = sc.nextLine();
        while (!parser.isTerminated(message)) {
            try {
                taskList = parser.process(message, taskList);
            } catch (BocchiException e) {
                ui.exception(e);
            }
            message = sc.nextLine();
        }
        sc.close();
        BocchiSaver saver = new BocchiSaver(taskList);
        saver.saveTaskList();
        ui.exit();
    }
}
