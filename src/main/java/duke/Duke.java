package duke;
import duke.commands.Commands;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.utilities.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;



/**
 * The main class for the Duke application.
 * Duke is a task management application that allows users to manage tasks.
 *
 */

public class Duke {
    private Storage storage;
    private Parser parser;
    private TaskList tasks;
    private Commands command;
    private Ui ui;

    /**
     * Constructs a Duke instance with the file path.
     * Initializes the user interface, task lists, commands and parser.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("src/main/MYBOT.txt");

        try {
            tasks = new TaskList(storage.loadTaskFromFile());
        } catch (NullPointerException e){
            tasks = new TaskList();
        } catch (Exception e) {
            ui.printFileError();
            new File("src/main/MYBOT.txt");
            tasks = new TaskList();
        }
        command = new Commands(ui,storage,tasks);
        parser = new Parser(ui, storage, tasks, command);
    }

    /**
     * Runs the Duke application.
     * Displays a greeting message, processes user commands from the command-line input
     * and provides responses accordingly. The loop until user commands bye.
     */
    public void run() {
        ui.showGreeting();

        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();

            if(input.equals("bye")) {
                ui.closeGreeting();
                break;
            } else {
                parser.analyseInput(input);
            }
        }
    }

    /**
     * converts the output of run from a system outprint to a string.
     *
     * @param input
     * @return
     */
    public String getResponse(String input) {
        // @@author tjch-o-reused
        // reused from iP with minor modifications
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOutput = System.out;
        System.setOut(new PrintStream(output));
        parser.analyseInput(input);
        System.setOut(originalOutput);
        return output.toString();
    }

    /**
     * The main entry point of the Duke application.
     * Creates a new instance of Duke with a specified file path and runs the application.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}

