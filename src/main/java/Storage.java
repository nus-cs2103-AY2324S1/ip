import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Storage {

    private String saveFile;

    public Storage(String saveFile) {
        this.saveFile = saveFile;
        this.createFileIfNotExists();
    }

    public void createFileIfNotExists() {
        File saveFile = new File(this.saveFile);
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
            FileReader fr = new FileReader(this.saveFile);
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
            FileWriter fw = new FileWriter(this.saveFile);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(taskList.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
