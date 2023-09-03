package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Duke {
    private final String FILE_PATH = "data/duke.txt";
    private Storage storage;
    private TaskList inputList;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        try {
            inputList = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(e);
            ui.showLoadingError();
            inputList = new TaskList();
        }
    }

    public void run() {
        ui.identity();
        Scanner userInput = new Scanner(System.in); // Create a Scanner object
        String userOutput = userInput.nextLine(); // Read user input
        while (!userOutput.equals("bye")) {
            Boolean isBye = Parser.command(userOutput, inputList);
            if (isBye) {
                break;
            }
            storage.createFile(inputList);
            userOutput = userInput.nextLine(); // Read user input
        }
        ui.printBye();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);
        Parser.command(input, inputList);
        storage.createFile(inputList);
        // Put things back
        System.out.flush();
        System.setOut(old);
        // Show what happened
        System.out.println("Here: " + baos.toString());
        return baos.toString();
    }
}
