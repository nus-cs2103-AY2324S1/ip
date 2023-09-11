package friday;

/**
 * Represents the main class for the Friday application.
 */
public class Friday {
    private TaskList taskList;
    private NoteList noteList;
    private Storage noteStorage;
    private Storage taskStorage;
    private Parser parser;

    /**
     * Constructs a new instance of the Friday application.
     * Initializes the user interface, task list, input scanner, storage, and parser.
     */
    public Friday() {
        this.taskList = new TaskList();
        this.noteList = new NoteList();
        this.taskStorage = new Storage("data/tasks.txt");
        this.noteStorage = new Storage("data/notes.txt");
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }

    /**
     * Generates a response based on user input.
     * @param input The user's input string.
     * @return A response string.
     */
    public String getResponse(String input) {
        String lowercaseInput = input.toLowerCase().trim();
        if (lowercaseInput.startsWith("note ")) {
            // Handle notes
            return parser.processNoteCommand(input, noteList, noteStorage);
        } else {
            // Handle tasks
            return parser.processTaskCommand(input, taskList, taskStorage);
        }
    }
}
