import java.io.IOException;

import com.alpha.commands.Command;
import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

/**
 * The type Alpha.
 */
public class Alpha {

    private final Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Instantiates a new Alpha.
     *
     * @param filePath File path of the local storage file.
     */
    public Alpha(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.load().getTasks());
        } catch (IOException | InvalidTaskException e) {
            ui.loadingError();
            taskList = new TaskList();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args Input arguments
     */
    public static void main(String[] args) {
        new Alpha("./data/save.txt").run();
    }

    /**
     * Run the aplication.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.proccessInput();
            Command command = Parser.parse(userInput);
            if (command == null) {
                continue;
            }
            command.execute(taskList, ui, storage);
            isExit = command.isExit();
        }
    }
}

