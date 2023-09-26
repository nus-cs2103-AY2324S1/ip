package Jarvis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // load data from dataFile
    public String load() {
        File file = new File(this.filePath);
        String fullData = "";
        try {
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) { // append each line and a \n  to the fullData variable
                    if (fullData.equals("")) {
                        fullData = line;
                    } else {
                        fullData = fullData + "\n" + line;
                    }
                }
                reader.close();
            } else {
                boolean fileCreated = file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullData;
    }

    // delete contents in dataFile
    public void deleteContents() {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            // Write an empty string to the file
            writer.write("");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    // write tasks in taskList to dateFile
    public void save(TaskList tasks) {
        for (int i = 0; i < tasks.countTask(); i++) { // writing the string of each task to the data file
            Task currentTask = tasks.getTask(i);
            try (FileWriter dataFileWriter = new FileWriter(this.filePath, true)) {
                dataFileWriter.write(currentTask.toString() + "\n");
            } catch (IOException e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }
}
