package sally;// deals with loading tasks from the file and saving tasks in the file

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(TaskList tasks) throws SallyException {
        try {
            File file = new File(this.filePath);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileWriter filewriter = new FileWriter(file);
            for (Task task : tasks.getTaskList()) {
                filewriter.write(task.toFileString() + "\n");
            }
            filewriter.close();
        } catch (IOException e) {
            throw new SallyException("OOPS! Something went wrong while saving your tasks.");
        }
    }

    public TaskList loadTasksFromFile() throws SallyException {
        TaskList tasks = new TaskList();
        try {
            File file = new File(this.filePath);
            if(!file.exists()) {
                return tasks;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = Task.fromFileString(line);
                tasks.addTask(task);
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new SallyException("OOPS! Something went wrong while loading your tasks.");
        }
        return tasks;
    }
}
