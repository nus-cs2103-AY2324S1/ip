import java.io.IOException;

/**
 * Represents Chatter the chatbot.
 *
 * @author Anthony Tamzil
 * @version CS2103T Individual Project AY2023/24 Semester 1
 */
public class Chatter {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * A constructor for the Chatter class
     */
    public Chatter(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }

    }

    /**
     * Check the user's commands and calls the appropriate methods according
     * to the commands.
     */
    private void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                this.ui.showDivider();
                String fullCommand = ui.readCommand();
                Parser p = new Parser(fullCommand);
                Command c = p.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (ChatterException e) {

            } finally {
                this.ui.showDivider();
            }
        }

        this.ui.showExit();
    }

    public static void main(String[] args) {
        new Chatter("./data/chatter.txt").run();
    }
}
