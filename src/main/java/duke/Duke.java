package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Duke {
    private final Ui ui;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        String FILE_PATH = "data/duke.txt";
        Storage storage = new Storage(FILE_PATH);

        try {
            storage.load();
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Handles the user input from the GUI
     *
     * @param input User input from GUI.
     * @return Bot output based on response.
     */
    String getResponse(String input) {
        // Create a stream to hold the output
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(stream);
        PrintStream old = System.out;
        System.setOut(ps);
        // process the user input
        try {
            Parser.processCommand(input, tasks);
            tasks.write();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.flush();
        System.setOut(old);
        return stream.toString();
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.greet();

        boolean isBotRunning = true;
        while (isBotRunning) {
            try {
                String scannerIn = scanner.nextLine();
                isBotRunning = Parser.processCommand(scannerIn, tasks);
                tasks.write();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
