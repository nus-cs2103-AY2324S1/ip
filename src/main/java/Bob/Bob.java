package Bob;

import Bob.command.Command;
import Bob.exception.BobException;
import Bob.parser.Parser;
import Bob.storage.StorageFile;
import Bob.task.*;
import Bob.ui.TextUi;

/**
 * A Chatbot that helps the user note down todos, deadlines and events.
 * It can be interacted with via the Command Line Interface
 */
public class Bob {
    private StorageFile storageFile;
    private TaskList taskList;
    private TextUi textUi;

    /**
     * Constructor of the Bob Class.
     *
     * @param fileDirectoryPath
     * @param fileName
     */
    public Bob(String fileDirectoryPath, String fileName) {
        textUi = new TextUi();
        storageFile = new StorageFile(fileDirectoryPath, fileName);
        try {
            taskList = storageFile.loadTasks();
        } catch (BobException e) {
            textUi.printErrorMessage(e);
            taskList = new TaskList();
        }
    }

    /**
     * Main entry point of the Chatbot. Terminates when
     * an exit command is called.
     */
    public void run() {
        textUi.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = textUi.readTextInput();
                Command c = Parser.parse(input);
                c.execute(taskList, storageFile, textUi);
                isExit = c.isExit();
            } catch (BobException e) {
                textUi.printErrorMessage(e);
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/", "Bob.txt").run();
    }
}
