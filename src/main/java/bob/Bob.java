package bob;

import bob.command.Command;
import bob.exception.BobException;
import bob.parser.Parser;
import bob.storage.StorageFile;
import bob.task.TaskList;
import bob.ui.TextUi;

/**
 * A ChatBot that helps the user note down todos, deadlines and events.
 * It can be interacted with via the Command Line Interface
 */
public class Bob {
    private StorageFile storageFile;
    private TaskList taskList;
    private TextUi textUi;

    /**
     * Constructor of the Bob Class.
     *
     * @param fileDirectoryPath Relative path to directory containing save file
     * @param fileName Name of save file
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
     * Main entry point of the ChatBot. Terminates when
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
            } finally {
                textUi.printDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Bob("data/", "Bob.txt").run();
    }
}
