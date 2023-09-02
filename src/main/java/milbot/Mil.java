package milbot;


/**
 * Mil class represents a chatbot application for managing tasks.
 */
public class Mil {
    private static Storage storage;
    private static TaskList taskList;
    private static Ui ui;
    private static Parser parser;

    /**
     * Constructs a new instance of the Mil chatbot.
     * Initializes the task list, user interface, storage, and parser.
     */
    public Mil() {
        ui = new Ui();
        storage = new Storage();
        taskList = storage.loadTasksFromFile();
        parser = new Parser(taskList, ui, storage);
    }

//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String input;
//        //ui.printWelcomeMessage();
//
//        while (scanner.hasNext()) {
//            input = scanner.nextLine();
//            parser.parseInput(input);
//        }
//
//    }
    public String getResponse(String input) {
        return parser.parseInput(input);
    }
}