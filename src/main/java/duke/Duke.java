package duke;

import duke.exceptions.DukeException;

import java.time.format.DateTimeFormatter;

public class Duke {

    /** Time format for the Max bot */
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy - HH:mm");
    /** Ui object to interact with user */
    private Ui ui;
    /** TaskList object to store tasks */
    private TaskList taskList;
    /** Storage object to interact with data file(s) */
    private Storage storage;

    /**
     * Constructs a bot.
     *
     * @param filePath Path of data file from root.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    /**
     * Initializes the bot.
     *
     * @param args Arguments to main.
     */
    public static void main(String[] args) {
        Duke max = new Duke("data/duke.txt");
        max.run();
    }

    /**
     * Runs the bot.
     */
    public void run() {
        this.ui.greet();;
        try {
            this.ui.getUserInput(this.taskList, this.storage);
        } catch (DukeException e) {
            System.out.println("\n" + e.getMessage());
        } finally {
            this.ui.exit();
        }
    }
}
