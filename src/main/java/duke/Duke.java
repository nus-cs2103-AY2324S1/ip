package duke;

/**
 * A class that represents a ChatBot.
 * Interacts with the user based on the input received.
 */
public class Duke {
    private TaskList taskList;
    private Parser parser;

    /**
     * Public Constructor for Duke.
     *
     * @param filepath of type String
     */
    public Duke(String filepath) {
        this.parser = new Parser();
        this.taskList = Storage.load(filepath, parser);
    }

    /**
     * Runs the program.
     * Reads the user inputs.
     */
    public void run() {
        Ui.printGreetings();
        while(true) {
            String input = Ui.scanInput();
            parser.handleInput(input, taskList, false);
            if (parser.isExit) {
                Ui.printBYE();
                break;
            }
        }
    }

    /**
     * Main method of the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
