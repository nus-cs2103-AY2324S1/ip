package oreo.storage;

import oreo.exception.IllegalDateTimeException;
import oreo.task.Task;
import oreo.task.TaskList;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final File storageFile;

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.storageFile = new File(filePath);
        loadFile();
    }

    private void loadFile() {
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile(TaskList tasks) throws FileNotFoundException, IllegalDateTimeException {
        Scanner sc = new Scanner(this.storageFile);
        while (sc.hasNext()) {
            int id = sc.nextInt();
            int mark = sc.nextInt();
            String description = sc.nextLine();
            Task newTask = Task.generateSavedTask(id, mark == 1, description);
            tasks.add(newTask);
        }
    }

    public void clearFile() {
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(TaskList tasks) throws IOException {
        clearFile();
        FileWriter fw = new FileWriter(filePath, true);
        int numberOfTasks = tasks.getNumberOfTask();
        for (int i = 0; i < numberOfTasks; i++) {
            String data = tasks.get(i).writeToFile();
            fw.write(data);
        }
        fw.close();
    }
}
