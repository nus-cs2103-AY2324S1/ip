package thea;

/**
 * Thea is a chatbot that manages user tasks.
 */
public class Thea {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    /**
     * Constructs a new Thea object.
     * Initializes Ui, Storage and TaskList objects.
     *
     * @param fileName the fileName in which the task data is/should be saved.
     */
    public Thea(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        this.parser = new Parser();
        try {
            this.tasks = new TaskList(storage.retrieveTasks());
        } catch (FileCorruptedException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Contains logic the main logic of Thea that needs to be run.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readNextLine();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.getIsExit();
            } catch (EmptyDescriptionException | WrongCommandException
                     | WrongDateTimeFormatException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String greetings() {
        return ui.greet();
    }
}

