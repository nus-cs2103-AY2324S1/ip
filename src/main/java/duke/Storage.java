package duke;

import dukeutilities.FileHandler;

public class Storage {
    private FileHandler taskFile;

    public Storage(String fileName, String directoryName) {
        this.taskFile = new FileHandler(fileName, directoryName);
        taskFile.createDirectoryIfNotPresent();
        taskFile.createFileIfNotPresent();
    }

    public void writeListToFile(TaskList taskList) {
        taskFile.writeListToFile(taskList.getList());
    }
}

