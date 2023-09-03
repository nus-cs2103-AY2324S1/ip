package duke;


/**
 * Class to encapsulate the whole chatbot and its functionality
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    private final Parser parser;
    private final SearchEngine searchEngine;

    /**
     * Constructor for Duke
     * @param filePath  the path of the file
     */
    public Duke(String filePath) {
        this.storage = Storage.createStorage(filePath);
        this.taskList = new TaskList(filePath);
        this.ui = new Ui();
        this.searchEngine = new SearchEngine(this.ui);
        this.parser = new Parser(this.taskList, this.storage, this.ui, this.searchEngine);
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        this.ui.showWelcome();
        boolean hasCommands = true;

        while (hasCommands) {
            String input = this.ui.readCommand();
            try {
                Command command = Command.getCommand(input);
                this.parser.parse(input, command);
                hasCommands = this.parser.shouldContinue();

            } catch (InvalidInputException e) {
                this.ui.showCommandError(input);
            }
        }
    }

    /**
     * Runs the chatbot with the given input
     * @param input the input to run the chatbot with
     */
    public void run(String input) {
        try {
            Command command = Command.getCommand(input);
            this.parser.parse(input, command);
        } catch (InvalidInputException e) {
            this.ui.showCommandError(input);
        }
    }

    /**
     * Prints the welcome message
     */
    public void welcome() {
        this.ui.showWelcome();;
    }


    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}
