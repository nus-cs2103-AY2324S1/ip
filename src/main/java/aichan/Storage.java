package aichan;

import aichan.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList tasks) throws AiChanException {
        try {
            File file = new File(this.filePath);
            FileWriter filewriter = new FileWriter(file);

            File directory = file.getParentFile();
            if (directory != null && !directory.exists()) {
                directory.mkdirs();
            }

            for (Task task : tasks.getTasks()) {
                String line = task.toFileLine();
                if (line != null) {
                    filewriter.write(line + "\n");
                }
            }
            filewriter.close();
        } catch (IOException e) {
            throw new AiChanException("Error saving the tasks into the file.");
        }
    }

    public ArrayList<Task> load() throws AiChanException {
        ArrayList<Task> arrTask = new ArrayList<>();

        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                return arrTask;
            }
            Scanner scn = new Scanner(file);
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                Task task = Task.stringToTask(line);
                if (task != null) {
                    arrTask.add(task);
                }
            }
            scn.close();
        } catch (IOException e) {
            throw new AiChanException("Error loading tasks from file.");
        }
        return arrTask;
    }

}
