package aj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class responsible for CRUD operations with database.
 */
public class Storage {

    Parser parser;
    final String FILEPATH;

    /**
     * Gets raw data from database and return task related to it.
     *
     * @param line Data from database.
     * @return Task associated to the data.
     * @throws WrongDescriptionException If command from user input does not exist.
     */
    public Task getTaskFromData(String line) throws WrongDescriptionException {
        String[] parsedValues = line.split(",");
        Scanner strScanner = new Scanner(parsedValues[0]);
        String command = strScanner.next().toLowerCase();
        String remaining = strScanner.nextLine();
        boolean isMark = Boolean.parseBoolean(parsedValues[1]);
        Task task;
        switch (command) {
        case "todo":
            task = this.parser.getTodoTask(remaining, isMark);
            break;
        case "deadline":
            task = this.parser.getDeadlineTask(remaining, isMark);
            break;
        case "event":
            task = this.parser.getEventTask(remaining, isMark);
            break;
        default:
            task = null;
            break;
        }
        return task;
    }

    /**
     * Reads data from database, creates the necessary Task objects and returns an Arraylist of Tasks.
     *
     * @return Arraylist of Tasks.
     */
    public List<Task> initialiseData() {
        File file = new File(this.FILEPATH);
        List<Task> taskList = new ArrayList<>();
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return null;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                taskList.add(getTaskFromData(line));
            }

        } catch (IOException | WrongDescriptionException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Set the 'isMarked' attribute of the Task at index 'idx' to 'isMarked' in database.
     *
     * @param idx      Index of the database (starting from 0).
     * @param isMarked True to mark Task as completed, false otherwise.
     * @throws IOException Arose if there is issue updating database.
     */
    public void updateData(int idx, boolean isMarked) throws IOException { // linenumber refers to index from 0
        Path myPath = Paths.get(this.FILEPATH);

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (i == idx) {
                String[] parsedValues = fileContent.get(i).split(",");
                String newLineContent = parsedValues[0] + "," + Boolean.toString(isMarked);
                fileContent.set(i, newLineContent);
                break;
            }
        }
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Deletes data of Task at index 'idx' from database.
     *
     * @param idx Index of that Task to be deleted (starting from 0).
     * @throws IOException Arose if there is issue updating database.
     */
    public void deleteData(int idx) throws IOException { // get linenumber and delete that entry
        Path myPath = Paths.get(this.FILEPATH);

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (i == idx) {
                fileContent.remove(i);
                break;
            }
        }
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Adds new Task into database.
     *
     * @param str Full data string to be stored in database.
     * @throws IOException Arose if there is issue updating database.
     */
    public void addData(String str) throws IOException { // get linenumber and delete that entry
        Path myPath = Paths.get(this.FILEPATH);

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));
        fileContent.add(str);
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    Storage(Parser parser, String filePath) {
        this.parser = parser;
        this.FILEPATH = filePath;
    }
}
