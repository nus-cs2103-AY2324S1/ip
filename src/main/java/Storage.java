import java.io.*;
import java.time.format.DateTimeParseException;

public class Storage {
    private static final String SAVE_FILE = "data/saved_tasks.csv";

    public void createFileIfNotExists() {
        File saveFile = new File(SAVE_FILE);
        if (!saveFile.exists()) {
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public TaskList readTasksFromFile() throws TaskFormatException, DateTimeParseException {
        TaskList taskList = new TaskList();
        try {
            FileReader fr = new FileReader(SAVE_FILE);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                taskList.addTask(Task.fromString(line));
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return taskList;
    }

    public void writeTasksToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(SAVE_FILE);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(taskList.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
