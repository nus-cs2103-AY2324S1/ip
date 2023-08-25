import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String directoryPath;
    private String fileName;
    public Storage(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.fileName = fileName;
        File directory = new File(this.directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public TaskList getTasks() {
        File file = new File(this.directoryPath + this.fileName);
        try {
            TaskList taskList = new TaskList();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = Task.parseFileString(scanner.nextLine());
                if (task != null) {
                    taskList.addTask(task);
                }
            }
            scanner.close();
            return taskList;
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

    public void saveTasks(TaskList taskList) throws TaskNumberException {
        try {
            FileWriter fileWriter = new FileWriter(this.directoryPath + this.fileName);
            for (int i = 1; i <= taskList.getSize(); i++) {
                fileWriter.write(taskList.getTask(i).composeToFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
