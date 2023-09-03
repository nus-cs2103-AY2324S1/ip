package jeeves.storage;

import jeeves.task.Deadline;
import jeeves.task.Event;
import jeeves.task.Task;
import jeeves.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Storage is responsible for all read/write/other interactions with the .txt datafile containing Jeeves's data.
 */
public class Storage {
    
    private final Path dataDirPath;
    private final Path dataFilePath;
    
    /**
     * Constructor for a Storage object.
     * Takes 2 different Strings and checks if the relative directory and datafile exists.
     * Creates them if they do not exist.
     * 
     * @param dirPathString Relative path for the directory in String format
     * @param filePathString Relative path for the data file in String format
     */
    public Storage(String dirPathString, String filePathString) {
        // Checks if the directory exists
        dataDirPath = Paths.get(dirPathString);
        // If the directory does not exist, create it for the user
        if (Files.notExists(dataDirPath)) {
            try {
                Files.createDirectories(dataDirPath);
            } catch (IOException e) {
                // Do nothing if an error is encountered since the directory existence is already checked
                // Theoretically impossible to enter this block
            }
        }
        dataFilePath = Paths.get(filePathString);
        // If the file does not exist, create it for the user
        if (Files.notExists(dataFilePath)) {
            try {
                Files.createFile(dataFilePath);
            } catch (IOException e) {
                // Do nothing if an error is encountered since the file existence is already checked
                // Theoretically impossible to enter this block
            }
        }
    }

    /**
     * Writes the provided data to the datafile using BufferedWriter.
     *
     * @param data The data to be written to the file in String format
     */
    public void writeTasklistToFile(String data) {
        // Writes the text to the output file
        try {
            BufferedWriter bw = Files.newBufferedWriter(dataFilePath);
            bw.write(data);
            bw.flush();
            bw.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the data from the datafile using BufferedReader.
     * Creates a new corresponding Task object for each line of valid data.
     *
     * @return Task ArrayList containing the tasks re-created from the datafile.
     */
    public ArrayList<Task> readTasklistFromFile() {
        // Initialization step for task list, adds an empty object so the arraylist is 1-indexed
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(null);
        try {
            BufferedReader br = Files.newBufferedReader(dataFilePath);
            String currLine;
            while ((currLine = br.readLine()) != null) {
                // Extract the information to populate the array list
                String[] currData = currLine.split("\\|");
                String taskType = currData[0];
                boolean status = Integer.parseInt(currData[1]) == 1;
                String desc = currData[2];
                switch (taskType) {
                    case "T":
                        taskList.add(new Todo(desc, status));
                        break;
                    case "D":
                        LocalDate deadline = LocalDate.parse(currData[3]);
                        taskList.add(new Deadline(desc, deadline, status));
                        break;
                    case "E":
                        String startTime = currData[3];
                        String endTime = currData[4];
                        taskList.add(new Event(desc, startTime, endTime, status));
                        break;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }
}
