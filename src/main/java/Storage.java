import duke.exceptions.InvalidFileTypeException;

import java.io.*;

import java.util.ArrayList;

public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws InvalidFileTypeException {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            ArrayList<Task> taskArrayList = new ArrayList<>();
            String line;
            Parser parser = new Parser();

            while ((line = reader.readLine()) != null) {
                taskArrayList.add(parser.parseSave(line));
            }
            return taskArrayList;
        } catch (IOException e) {
            throw new InvalidFileTypeException("Error: File not found");
        }
    }

    public void save(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            writer.write(taskList.toTaskSave());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
