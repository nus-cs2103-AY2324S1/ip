package alpha;

/**
 * The Chatbot Alpha is capable of tracking, marking, listing, and deleting events, to-dos, and deadlines.
 * It responds to commands like "event," "meeting," "deadline," "list," "mark," "unmark," and "delete."
 * To end the Chatbot, type "bye."
 *
 * @author Wong Joon Hung
 */
public class Alpha {

    private UI ui;
    private FileHandler fileHandler;
    private TaskList taskList;
    private Parser parser;

    /**
     * Constructor for the Alpha class. It reads existing tasks from "data/alpha.txt" and adds them to the taskList.
     */
    public Alpha() {
        ui = new UI();
        fileHandler = new FileHandler();
        taskList = fileHandler.readFromFile();
        parser = new Parser(fileHandler, taskList, ui);
        fileHandler.checkAndCreate();
    }

    /**
     * Starts the Alpha ChatBot. Ends when "bye" is input.
     */
    public void run() {
        ui.introduce();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.read();
            Command command = parser.parse(input);
            command.execute();
            isExit = command.isExit();
        }
        ui.goodbye();
    }

    /**
     * This is the main method that calls the run() function and starts Chatbot Alpha.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Alpha().run();
    }
}
