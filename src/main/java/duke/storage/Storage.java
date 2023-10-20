package duke.storage;

import duke.task.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;



/**
 * This class handles the storage and retrieval of tasks to and from a file.
 */
public class Storage {

    private String filePath;
    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path of the file to be used for storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file and returns a TaskArray containing the parsed tasks.
     *
     * @return A TaskArray containing the loaded tasks.
     */
    public TaskArray load() {
        // Create the folder/file if it doesn't exist
        createFolder();
        createFile();

        ArrayList<String> result = new ArrayList();

        ArrayList<String> toBeProcessedArray = scanFile(this.filePath);
        return parseData(toBeProcessedArray);
    }

    /**
     * Creates a new file at the specified file path if it doesn't exist.
     *
     * @param filePath The path of the file to be created.
     * @return True if the file already exists, false if a new file was created.
     */
    public boolean createFile() {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File already exists.");
            return true;
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("File created successfully.");

        return false;
    }

    /**
     * Creates a folder at the specified path if it doesn't exist.
     *
     * @param folderPath The path of the folder to be created.
     * @return True if the folder already exists, false if a new folder was created.
     */
    public boolean createFolder() {
        File folder = new File(filePath).getParentFile();

        if (folder.exists()) {
            System.out.println("Folder already exists.");
            return true;

        }
        boolean folderCreated = folder.mkdirs();
        if (folderCreated) {
            System.out.println("Folder created successfully.");
        } else {
            System.out.println("Failed to create folder.");
        }
        return false;

    }

    /**
     * Scans the specified file and returns its contents as an ArrayList of lines.
     *
     * @param fileName The name of the file to be scanned.
     * @return An ArrayList containing the lines of the file.
     */
    public ArrayList<String> scanFile(String fileName) {

        ArrayList<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Parses the data from the input list and returns a TaskArray containing the tasks.
     *
     * @param inputList The ArrayList of strings to be parsed into tasks.
     * @return A TaskArray containing the parsed tasks.
     */
    public TaskArray parseData(ArrayList<String> inputList) {

        
        ArrayList<Task> taskArrayList = new ArrayList<>();

        for (String input : inputList) {
            Task newTask = inputToTask(input);
            if (newTask != null) {
                taskArrayList.add(newTask);
            }
        }

        return new TaskArray(taskArrayList);
    }

    public Task inputToTask(String input) {
        String[] parts = input.split(";");
        String type = parts[0];
        String text = parts[1];
        boolean checked = parts[2].equals("true");
        Tag tag = Tag.generateTag(parts[3]);


        switch (type) {
            case "T":
                return new ToDo(text,checked,tag);

            case "E":
                LocalDateTime startDate = LocalDateTime.parse(parts[4]);
                LocalDateTime endDate = LocalDateTime.parse(parts[5]);
                return new Event(text,startDate,endDate,checked,tag);

            case "D":
                LocalDateTime dueDate = LocalDateTime.parse(parts[4]);
                return new Deadline(text,dueDate,checked,tag);
        }

        return null;
    }

    /**
     * Formats the tasks from the TaskArray into an ArrayList of strings.
     *
     * @param taskArray The TaskArray containing the tasks to be formatted.
     * @return An ArrayList of strings representing the formatted tasks.
     */
    public ArrayList<String> formatData(TaskArray taskArray) {
        ArrayList<Task> taskArrayList = taskArray.getTaskArrayList();

        ArrayList<String> output = new ArrayList<>();

        for (Task task : taskArrayList) {
            String input = task.getParsedTask();
            output.add(input);
        }
        assert output.size() == taskArrayList.size(): "Not All Data in Task Array being formatted into Output";
        return output;

    }

    /**
     * Writes the formatted data from the inputArray into the specified file.
     *
     * @param inputArray The ArrayList of strings to be written to the file.
     * @param filePath The path of the file to write the data to.
     */
    public void inputFile(ArrayList<String> inputArray, String filePath) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (String line : inputArray) {
                writer.write(line);
                writer.newLine(); // Add a newline after each line
            }
            writer.close(); // Close the writer to flush changes

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Uploads the data from the TaskArray to the storage file.
     *
     * @param taskArray The TaskArray containing the tasks to be uploaded.
     */
    public void upload(TaskArray taskArray) {
        inputFile(formatData(taskArray),this.filePath);
    }
}
