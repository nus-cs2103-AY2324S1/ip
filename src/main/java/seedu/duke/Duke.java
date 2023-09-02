package seedu.duke;

import seedu.duke.Exceptions.LemonException;
import seedu.duke.datafile.Storage;
import seedu.duke.parser.Parser;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (LemonException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMessage();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            //System.out.println("in while loop");
            String input = scanner.nextLine();
            //System.out.println("Received input: " + input); // Provide feedback to the user
            if (input.equalsIgnoreCase("bye")) {
                isRunning = false;
            } else {
                //System.out.println("parsing soon");
                try {
                    Parser.parseTasks(input, tasks, storage, ui);
                } catch (LemonException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        ui.bye();
    }



    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}