import java.io.IOException;

import com.alpha.commands.Command;
import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

public class Alpha {

    private final Ui ui;
    private Storage storage;
    private TaskList taskList;

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

    public static void main(String[] args) {
        new Alpha("./data/save.txt").run();
    }

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

