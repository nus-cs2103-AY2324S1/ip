package duke;

/**
 * The chatbot object. Contains the logic of the chatbot.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Creates the Duke chatbot object.
     *
     * @param filePath The path to store the tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Runs the chatbot with the given String input.
     */
    public boolean run(String text) {
        boolean isExit = false;
        String response = "";
        try {
            if (text.isEmpty()) {
                throw new DukeException("Sorry, empty command is not supported");
            }
            Command c = Parser.parse(text);
            isExit = c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return isExit;

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse() {
        return this.ui.respond();
    }
}


///**
// * The chatbot object and also the entry point to start the chatbot.
// */
//public class Duke extends Application {
//    private TaskList tasks;
//    private Storage storage;
//    private Ui ui;
//
//    /**
//     * Creates the Duke chatbot object.
//     *
//     * @param filePath The path to store the tasks.
//     */
//    public Duke(String filePath) {
//        this.ui = new Ui();
//        try {
//            this.storage = new Storage(filePath);
//            this.tasks = new TaskList(this.storage.load());
//        } catch (DukeException e) {
//            ui.showError(e.getMessage());
//        }
//    }
//
//    /**
//     * Runs the chatbot with the main looping logic.
//     */
//    public void run() {
//        this.ui.greet();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                // ui reads in one line of input
//                String text = ui.readCommand();
//                if (text.isEmpty()) {
//                    throw new DukeException("Sorry, empty command is not supported");
//                }
//                Command c = Parser.parse(text);
//                isExit = c.execute(this.tasks, this.ui, this.storage);
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//        ui.goodBye();
//    }
//
//    public static void main(String[] args) {
//        Duke bot = new Duke("data/tasks.txt");
//        bot.run();
//    }
//}
