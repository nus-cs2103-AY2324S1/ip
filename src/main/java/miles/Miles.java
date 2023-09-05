package miles;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
     * @param filePath The path to the file where the tasks are stored.
     */
    public Miles(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, directoryPath);
        this.taskList = this.storage.loadFile();
    }

    /**
     * Runs the bot with the bot taking input from users.
     */
    public void run() {
        this.ui.greet();
        boolean shouldExit = false;
        Scanner scanner = new Scanner(System.in);
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

    /**
     * Runs the bot with the given input.
     * @param input the input to run the chatbot with
     */
    public void run(String input) {
        Parser parser = new Parser();

        try {
            Command c = parser.parse(input);
            c.execute(this.taskList, this.ui, this.storage);
        } catch (MilesException e) {
            this.ui.printErrorMsg(e.getMessage());
        }
    }

    /**
     * Returns the response of the bot to the given input.
     * @param input the input to run the chatbot with
     * @return      the response of the bot
     */
    public String getResponse(String input) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        // redirect System.out to output stream to capture everything that is printed
        System.setOut(new PrintStream(output));
        run(input);
        // restore the original output stream
        System.setOut(originalOut);
        return output.toString();
    }

    /**
     * Returns the greeting of the bot.
     * @return the greeting of the bot
     */
    public String getGreeting() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));
        ui.greet();
        System.setOut(originalOut);
        return output.toString();
    }
    
    public static void main(String[] args) {
        new Miles(filePath).run();
    }
}
