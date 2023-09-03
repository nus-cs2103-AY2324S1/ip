package duke;

import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList inputList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
        new Duke("data/duke.txt").run();
    }
}
