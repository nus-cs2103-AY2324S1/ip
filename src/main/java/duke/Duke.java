package duke;

import commands.Command;
import parser.Parser;
import storage.DataFile;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;
import java.io.*;
import java.util.ArrayList;

/**
 * The programme that runs the Duke chatbot.
 */
public class Duke {

    private final Ui ui;
    private final DataFile dF;
    private TaskList tL;

    /**
     * Duke constructor that takes a String, String and initialises
     * class variables.
     * @param filePath Name of the path.
     * @param fileName Name of the file.
     */
    public Duke(String filePath, String fileName) {
        ui = new Ui();
        dF = new DataFile(filePath, fileName);
        try {
            tL = new TaskList(dF.fileToObjects());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            tL = new TaskList(new ArrayList<Task>());
        }

    }

    /**
     * The main method.
     * @param args Unused.
     */
    public static void main(String[] args) {
        String filePath = "./data/";
        String fileName = "trying.txt";
        new Duke(filePath, fileName).run();
    }

    /**
     * The run logic of the chatbot.
     */
    public void run() {
        ui.welcomeMsg();
        boolean run = true;
        while (run) {
            try {
                String command = ui.readCommand();
                Command c = new Parser().getCommand(command);
                c.execute(tL, dF);
                run = !c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
