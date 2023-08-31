import brotherman.commands.Command;
import brotherman.exceptions.BrothermanException;
import brotherman.parser.Parser;
import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

/**
 * Represents a Brotherman chatbot
 */
public class Brotherman {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Brotherman
     * @param filepath File path of the storage
     */
    public Brotherman(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = storage.readFromFile();
    }

    /**
     * Runs the Brotherman chatbot
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (BrothermanException e) {
                System.out.println("should be a show error message");
                ui.showLine();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Brotherman("./data/brotherman.txt").run();
    }
}
