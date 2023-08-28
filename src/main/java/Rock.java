import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Rock is the name of and the main program used
 * to run the chatbot for ip.
 * 
 * @author Alvis Ng (supermii2)
 */
public class Rock {
    public static Path FILE_PATH = Paths.get("data", "tasks.ser");
    public TaskList taskList;
    public Storage storage;
    public Ui ui;
    private Commands commands;
    private boolean isTerminated = false;


    public Rock(Path path) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.commands = new Commands(this);
        this.ui.startup();
    }

    public void run() {
        Invoker invoker = new Invoker(this.commands);
        while (!isTerminated) {
            String userInput = this.ui.getInput();
            try {
                invoker.handle(userInput);
            } catch (RockError e) {
                this.ui.respond(e.getMessage());
            }
        }
    }

    public void terminate() {
        // Sets necessary fields to closed.
        this.isTerminated = true;
        ui.close();
    }
    public static void main(String[] args) {
        new Rock(FILE_PATH).run();
    }
}
