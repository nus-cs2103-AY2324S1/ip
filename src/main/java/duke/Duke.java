package duke;

import exceptions.ErrorStorageException;
import helpers.Message;
import helpers.Storage;
import helpers.TaskList;

/**
 * Main Class of controllers.Duke
 */
public class Duke {

    private TaskList taskList;
    private final String DIRECTORY = "data";
    private Storage storage;
    private Message message;

    /**
     * Empty public constructor to load controllers.Duke
     */
    public Duke() {
    }

    /**
     * Public constructor to load controllers.Duke
     *
     * @param filePath File path to get text file data from
     * @throws ErrorStorageException Exception for storage loading error
     */
    public Duke(String filePath) throws ErrorStorageException {
        message = new Message();
        String projRoot = System.getProperty("user.dir");
        String path = projRoot + "/" + DIRECTORY + filePath;
        this.storage = new Storage(path);
        try {
            taskList = new TaskList(this.storage.read());
        } catch (Exception e) {
            message.showLoadingError();
            taskList = new TaskList();
        }

    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }
}
