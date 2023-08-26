package duke.lib;

import duke.error.DukeException;
import duke.task.TaskList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File saveFile;
    String directoryPath;
    String fullFilePath;

    public Storage(String directoryName, String fileName) {
        this.directoryPath = "./" + directoryName;
        this.fullFilePath = this.directoryPath + "/" + fileName;

        this.saveFile = new File(this.fullFilePath);
    }

    private void createIfMissing() throws DukeException {
        try {
            File directory = new File(this.directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            if (!this.saveFile.exists()) {
                this.saveFile.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("Unexpected error occurred. Cannot create new save file!");
        }
    }

    public ArrayList<String> load() throws DukeException {
        ArrayList<String> serializedTasks = new ArrayList<>();
        try {
            Scanner saveFileReader = new Scanner(this.saveFile);
            while (saveFileReader.hasNextLine()) {
                serializedTasks.add(saveFileReader.nextLine());
            }
            saveFileReader.close();
        } catch (FileNotFoundException e1) {
            this.createIfMissing();
        } catch (Exception e) {
            throw new DukeException("File save is corrupted");
        }
        return serializedTasks;
    }

    public void save(TaskList tasks) throws DukeException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fullFilePath))) {
            tasks.save(writer);
        } catch (IOException e) {
            throw new DukeException("Failed to save!");
        }
    }
}
