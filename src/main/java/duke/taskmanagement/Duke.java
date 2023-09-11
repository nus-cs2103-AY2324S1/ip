package duke.taskmanagement;

public class Duke {
    String PATH = "./data/duke.txt";
    private Ui ui = new Ui();
    private TaskList tasks;
    private Storage storage = new Storage(PATH);

    public Duke () {
        tasks = new TaskList(this.ui, storage.loadData(), storage);
    }

    /**
     * Return a String that contains the intended message
     * after getting user's input
     *
     * @param input The input from user.
     * @return String message to the user.
     */
    public String getResponse(String input) {
        String greetingMessage = ui.greet();
        assert greetingMessage == "Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n" : "greeting should contain default greet";
        Parser parser = new Parser(this.ui, this.tasks);
        assert parser != null : "parser should not be null at any point";
        String messageToUser = parser.readCmd(this.tasks, input);
        return messageToUser;
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}
