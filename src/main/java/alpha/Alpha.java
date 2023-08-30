package alpha;
/**
 * The Chatbot Alpha that is able to track, mark, list and delete events, to-dos and deadlines.
 * It responds to commands like "event", "meeting", "deadline", "list", "mark", "unmark", "delete".
 * To end the Chatbot, type "bye".
 *
 * @author Wong Joon Hung
 */
public class Alpha {

    private UI ui;
    private FileHandler fh;

    private TaskList taskList;

    private Parser parser;

    /**
     * Constructor for the class Alpha. It reads existing tasks from "data/alpha.txt" and adds it to the taskList.
     */
    public Alpha() {
        ui = new UI();
        fh = new FileHandler();
        taskList = fh.readFromFile();
        parser = new Parser(fh, taskList, ui);
        fh.checkAndCreate();
    }

    /**
     * Starts the Alpha ChatBot. Ends when "bye" is inputted.
     */
    public void run() {
        ui.introduce();
        boolean isExit = false;
        while(!isExit) {
                String input = ui.read();
                Command c = parser.parse(input);
                c.execute();
                isExit = c.isExit();
        }
        ui.goodbye();
    }

    /**
     * This is the main method which calls the run() function and starts the Chatbot Alpha.
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Alpha().run();
    }
}
