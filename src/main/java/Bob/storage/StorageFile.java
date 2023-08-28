package Bob.storage;

import Bob.exception.BobException;
import Bob.task.Task;
import Bob.task.TaskList;
import Bob.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageFile {
    private String fileDirectoryPath;
    private String fileName;
    public StorageFile(String fileDirectoryPath, String fileName) {
        this.fileDirectoryPath = fileDirectoryPath;
        this.fileName = fileName;
    }
    public void saveTasks(TaskList taskList) throws BobException {
        this.checkDirectoryExists();
        try {
            FileWriter fileWriter = new FileWriter(this.fileDirectoryPath + this.fileName);
            for (int i = 0; i < taskList.size(); i++) {
                fileWriter.write(taskList.getTask(i).convertToFileFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new BobException("Something went wrong when saving your tasks :(");
        }
    }

    public TaskList loadTasks() throws BobException {
        this.checkDirectoryExists();
        try {
            File taskFile = new File(this.fileDirectoryPath + this.fileName);
            Scanner fileScanner = new Scanner(taskFile);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                Task task = Parser.parseStoredTask(fileScanner.nextLine());
                loadedTasks.add(task);
            }
            fileScanner.close();
            return new TaskList(loadedTasks);
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    private void checkDirectoryExists() throws BobException {
        File dataDirectory = new File(this.fileDirectoryPath);
        if (!dataDirectory.exists()) {
            boolean isMkDirSuccess = dataDirectory.mkdir();
            if (!isMkDirSuccess) {
                throw new BobException("Something went wrong when loading saving/loading your tasks :(");
            }
        }
    }
}
