package spot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import spot.exception.SpotException;
import spot.task.Deadline;
import spot.task.Event;
import spot.task.Task;
import spot.task.ToDo;

/**
 * Represents the file storage system of the chatbot.
 */
public class Storage {

    private static final String DIRECTORY_NAME = "./data";
    private static final String FILE_NAME = "spot.txt";
    private static final String FULL_PATH = DIRECTORY_NAME + "/" + FILE_NAME;
    private File storage;

    /**
     * Constructs a new Storage object.
     */
    public Storage() throws SpotException {
        try {
            File directory = new File(DIRECTORY_NAME);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(FULL_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            this.storage = file;
        } catch (IOException e) {
            throw new SpotException(e.getMessage());
        }
    }

    /**
     * Loads tasks from storage.
     *
     * @return ArrayList of Tasks containing all stored tasks.
     * @throws SpotException  If the file path cannot be found.
     */
    public ArrayList<Task> loadTasks() throws SpotException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner fileScanner = new Scanner(storage);
            while (fileScanner.hasNextLine()) {
                String task = fileScanner.nextLine();
                String[] keywords = task.trim().split("\\Q | \\E");
                if (keywords[0].equals("T")) {
                    if (keywords[1].equals("X")) {
                        tasks.add(new ToDo(keywords[2], true));
                    } else {
                        tasks.add(new ToDo(keywords[2], false));
                    }
                } else if (keywords[0].equals("D")) {
                    LocalDate deadline = LocalDate.parse(keywords[3],
                            DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    if (keywords[1].equals("X")) {
                        tasks.add(new Deadline(keywords[2], true,
                                deadline));
                    } else {
                        tasks.add(new Deadline(keywords[2], false,
                                deadline));
                    }
                } else {
                    LocalDate start = LocalDate.parse(keywords[3],
                            DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalDate end = LocalDate.parse(keywords[4],
                            DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    if (keywords[1].equals("X")) {
                        tasks.add(new Event(keywords[2], true, start, end));
                    } else {
                        tasks.add(new Event(keywords[2], false, start, end));
                    }
                }
            }
            fileScanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new SpotException(e.getMessage());
        }
    }

    /**
     * Saves tasks into storage.
     *
     * @param tasks TaskList to be stored into a text file.
     * @throws SpotException  If the text file cannot be opened.
     */
    public void saveTasks(TaskList tasks) throws SpotException {
        try {
            FileWriter fileWriter = new FileWriter(Storage.FULL_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.getTask(i);
                bufferedWriter.write(task.toLine());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new SpotException(e.getMessage());
        }
    }
}
