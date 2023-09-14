package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import duke.command.Command;

/**
 * Shows the chat bot, Rion.
 */
public class Duke {
    /** Storage object that deals with loading and saving tasks. */
    private Storage storage;
    /** The unique task list containing all tasks inputted. */
    private TaskList taskList;
    /** The Ui that deals with interactions with the users. */
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            taskList = new TaskList(storage.loadList());
        } catch (DukeException e) {
            ui.printMessage(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public void runRion() {
        this.ui.getGreeting();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();

        while (!isExit) {
            String input = scanner.nextLine();
            try {
                Command c = parser.parse(input);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                this.ui.printMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    public void run(String input) {
        Parser parser = new Parser();

        try {
            Command c = parser.parse(input);
            c.execute(this.taskList, this.ui, this.storage);
        } catch (DukeException e) {
            this.ui.printMessage(e.getMessage());
        }
    }
    
    public String getResponse(String input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        run(input);
        System.setOut(originalOut);
        return output.toString();
    }

    public String getGreeting() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        ui.getGreeting();
        System.setOut(originalOut);
        return output.toString();
    }

    public static void main(String[] args) {
        Duke rion = new Duke("./data/tasks.txt");
        rion.runRion();
    }
}
