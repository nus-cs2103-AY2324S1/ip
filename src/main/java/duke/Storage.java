package duke;

import dukeutilities.FileHandler;

/**
 * The Storage class manages the file handling for task data storage.
 * It creates directories and files if not present and writes task data to files.
 */
public class Storage {
    private FileHandler taskFile;

    /**
     * Storage constrictor creates 'Data' directory if not found and 'Duke.txt' if not found.
     */
    public Storage(String fileName, String directoryName) {
        this.taskFile = new FileHandler(fileName, directoryName);
        taskFile.createDirectoryIfNotPresent();
        taskFile.createFileIfNotPresent();
    }

    /**
     * Writes the task list to the file.
     *
     * @param taskList The task list to be written to the file.
     */
    public void writeListToFile(TaskList taskList) {
        taskFile.writeListToFile(taskList.getList());
    }
}

