package duke;

enum TaskType {
    TODO, DEADLINE, EVENT
}

/**
 * The Tired class launches the Tired chatbot.
 * It serves as the entry point for the application.
 *
 */
public class Tired {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Tired chatbot with the specified file name for task data storage.
     *
     * @param fileName The name of the file where task data is stored.
     */
    public Tired(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Tired chatbot, displaying a welcome message and entering a main execution loop.
     * The chatbot continuously reads user commands, processes them, and provides responses.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isActive = true;
        while (isActive) {
            System.out.println("____________________________________________________________");
            String input = ui.readCommand().trim();
            try {
                isActive = Parser.isValidCommand(input, tasks, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            storage.saveToFile(tasks);
        }
        ui.showGoodbyeMessage();
    }

    /**
     * The main method that launches the Tired chatbot.
     *
     * @param args Refers to command-line arguments.
     */
    public static void main(String[] args) {
        String fileName = "duke.txt";
        new Tired(fileName).run();
    }
}