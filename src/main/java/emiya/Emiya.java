package emiya;

import emiya.emiyaexception.CreateDirectoryFailException;
import emiya.emiyaexception.InvalidDateException;
import emiya.emiyaexception.WrongDateFormatException;
import emiya.parser.Parser;
import emiya.storage.Storage;
import emiya.task.TaskList;
import emiya.ui.Ui;

/**
 * The main class that contains all the necessary components for the app to work.
 */
public class Emiya {
    private String dirName;
    private String fileName;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Emiya(String dirName, String fileName) {
        this.dirName = dirName;
        this.fileName = fileName;
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser();
        ui = new Ui();
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public Ui getUi() {
        return this.ui;
    }

    public Parser getParser() {
        return this.parser;
    }

    public String getDirName() {
        return this.dirName;
    }

    public String getFileName() {
        return this.fileName;
    }

    /**
     * Load the contents of the specified file into the task list.
     */
    public void loadList() {
        try {
            storage.createDirectory(dirName);
            storage.createFileInDirectory(fileName, dirName);
            storage.fillListWithFileContent(taskList, storage.fileContents(fileName, dirName));
        } catch (CreateDirectoryFailException | InvalidDateException | WrongDateFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}
