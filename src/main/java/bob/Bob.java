package bob;

import bob.command.Command;
import bob.exception.BobException;
import bob.parser.Parser;
import bob.storage.StorageFile;
import bob.task.TaskList;
import bob.ui.TextGenerator;
import bob.ui.TextUi;

/**
 * A ChatBot that helps the user note down todos, deadlines and events.
 * It can be interacted with via the Command Line Interface
 */
public class Bob {
    private static final String defaultDirectoryPath = "data/";
    private static final String defaultFileName = "Bob.txt";
    private StorageFile storageFile;
    private TaskList taskList;
    private TextUi textUi;
    private boolean isActive;

    /**
     * Constructor of the Bob Class.
     *
     * @param fileDirectoryPath Relative path to directory containing save file
     * @param fileName Name of save file
     */
    public Bob(String fileDirectoryPath, String fileName) {
        textUi = new TextUi();
        storageFile = new StorageFile(fileDirectoryPath, fileName);
        isActive = true;
        try {
            taskList = storageFile.loadTasks();
            assert taskList != null : "A null value has been returned when loading task list";
        } catch (BobException e) {
            textUi.printErrorMessage(e);
            taskList = new TaskList();
        }
    }

    /**
     * Default constructor for Bob Class that utilises
     * default directory path and file name.
     */
    public Bob() {
        textUi = new TextUi();
        storageFile = new StorageFile(defaultDirectoryPath, defaultFileName);
        isActive = true;
        try {
            taskList = storageFile.loadTasks();
            assert taskList != null : "A null value has been returned when loading task list";
        } catch (BobException e) {
            textUi.printErrorMessage(e);
            taskList = new TaskList();
        }
    }

    /**
     * Executes a command to read or modify the task list if user input abides
     * by a given command format. Returns a crafted String message based on
     * command executed or error prompted.
     *
     * @param input User input
     * @return A message to displayed to user
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Parser has returned a null value instead of a Command instance";
            c.execute(taskList, storageFile);
            if (c.isExit()) {
                isActive = false;
            }
            return c.getOutputMessage();
        } catch (BobException e) {
            return TextGenerator.getErrorMessage(e);
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public String getBobWelcomeMessage() {
        return TextGenerator.getWelcomeMessage();
    }
}
