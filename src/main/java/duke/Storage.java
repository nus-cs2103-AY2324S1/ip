package duke;

import duke.exceptions.DukeDatabaseException;
import duke.exceptions.DukeException;
import duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages storage operations such as saving and loading tasks from a file.
 */
public class Storage {

    /** File path of the database. */
    private Path filePath;

    /**
     * Constructs a new Storage instance.
     *
     * @param filePath The path to the database file.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the database file.
     *
     * @return An array of loaded tasks.
     * @throws DukeException If there's an error reading the database.
     */
    public Task[] loadData() throws DukeException {
        File db = new File(this.filePath.toUri());
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(db);
            while (fileReader.hasNextLine()) {
                taskList.add(readEntry(fileReader.nextLine()));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            this.createDatabase();
        }
        return taskList.toArray(new Task[0]);
    }


    /**
     * Creates a new database file.
     *
     * @throws DukeException If there's an error creating the database.
     */
    private void createDatabase() throws DukeException {
        File db = new File(this.filePath.toUri());
        File dir = new File(db.getParent());
        dir.mkdir();
        try {
            db.createNewFile();
        } catch (IOException e) {
            throw new DukeDatabaseException();
        }
    }

    /**
     * Reads and constructs a task based on the given database entry.
     *
     * @param entry The string representation of a task from the database.
     * @return The task represented by the entry.
     * @throws DukeException If there's an error reading the entry.
     */
    private Task readEntry(String entry) throws DukeException {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (fields[0]) {
            case "T":
                taskToAdd = new Todo(fields[2]);
                break;
            case "E":
                taskToAdd = new Event(fields[2],
                        LocalDateTime.parse(fields[3], formatter),
                        LocalDateTime.parse(fields[4], formatter));
                break;
            case "D":
                taskToAdd = new Deadline(fields[2], LocalDateTime.parse(fields[3], formatter));
                break;
            default:
                throw new DukeDatabaseException();
        }
        if (Integer.parseInt(fields[1]) == 1) {
            taskToAdd.mark();
        }
        return taskToAdd;
    }

    /**
     * Saves the list of tasks to the database file.
     *
     * @param todoList The list of tasks to save.
     * @throws IOException If there's an error writing to the file.
     */
    public void saveFile(Tasklist todoList) throws IOException {
        if (!Files.exists(filePath.getParent())) {
            try {
                // Create the directory
                Files.createDirectories(filePath.getParent());
                System.out.println("Directory created.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error creating directory.");
            }
        }
        if (!Files.exists(filePath)) {
            try {
                // Create the file
                Files.createFile(filePath);
                System.out.println("File created.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error creating file.");
            }
        }
        FileWriter fw = new FileWriter(String.valueOf(filePath), false);
        BufferedWriter bw = new BufferedWriter(fw);
        todoList.saveList(bw);
        bw.close();
        fw.close();
    }

}