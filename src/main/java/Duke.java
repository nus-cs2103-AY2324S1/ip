import java.io.IOException;

/**
 * This program is a chatbot, somebodyhaha, used to mark completion of tasks
 * marking the completion of tasks.
 *
 * @author: Low Jun Yu
 * @version: CS2103T AY23/24 Semester 1
 */
public class Duke {

    private final Storage STORAGE;
    private TaskList tasklst;
    private final Ui UI;

    /**
     * Duke class that initialises a Duke Chatbot.
     * @param filePath path location of the file to read previous stores
     */
    public Duke(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            tasklst = new TaskList(STORAGE.load());
        } catch (DukeException e){
            UI.showLoadingError();
            tasklst = new TaskList();
        }
    }

    /**
     * Runs the duke bot.
     */
    public void run() {
        UI.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try{
                String fullCommand = UI.read();
                UI.showLine();
                Command c = Parser.parseUserInput(fullCommand);
                c.execute(tasklst, UI, STORAGE);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                UI.showError(e.getMessage());
            } finally {
                UI.showLine();
            }
        }
    }

    /**
     * The program reads input given by the user to perform functions to help
     * add, edit, track and delete tasks inputted.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
