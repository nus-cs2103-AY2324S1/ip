import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.*;
import java.nio.file.*;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> loadData() throws DukeDatabaseNotFoundException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File file = new File(this.filepath);
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                try {
                    ArrayList<String> parsedEntry = Parser.parseDatabaseEntry(entry);
                    taskList.add(readEntry(parsedEntry));
                } catch (DukeDatabaseInvalidEntryException e) {
                    System.out.println(e);
                } catch (DukeEndDateBeforeStartDateException e) {
                    System.out.println(e);
                }
            }
            fileReader.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeDatabaseNotFoundException();
        }
    }

    public void saveData(TaskList taskList) {
        try {
            createDataDirectory();
            createDukeFile();
            writeTaskListToFile(taskList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private Task readEntry(ArrayList<String> parsedEntry) throws DukeDatabaseInvalidEntryException,
            DukeEndDateBeforeStartDateException {
        Task newTask;
        switch (parsedEntry.get(0)) {
            case "T":
                newTask = new Todo(parsedEntry.get(2));
                if (parsedEntry.get(1).equals("X")) {
                    newTask.markAsDone();
                }
                break;
            case "D":
                newTask = new Deadline(parsedEntry.get(2), formatDate(parsedEntry.get(3)));
                if (parsedEntry.get(1).equals("X")) {
                    newTask.markAsDone();
                }
                break;
            case "E":
                newTask = new Event(parsedEntry.get(2), formatDate(parsedEntry.get(3)),
                        formatDate(parsedEntry.get(4)));
                if (parsedEntry.get(1).equals("X")) {
                    newTask.markAsDone();
                }
                break;
            default:
                throw new DukeDatabaseInvalidEntryException();
        }
        return newTask;
    }

    private void createDataDirectory() throws IOException {
        File dataDirectory = new File(this.filepath).getParentFile();
        if (!dataDirectory.exists()) {
            if (!dataDirectory.mkdirs()) {
                throw new IOException("Failed to create data directory");
            }
        }
    }

    private void createDukeFile() throws IOException {
        File dukeFile = new File(this.filepath);
        if (!dukeFile.exists()) {
            if (!dukeFile.createNewFile()) {
                throw new IOException("Failed to create duke file");
            }
        }
    }

    private void writeTaskListToFile(TaskList taskList) throws IOException {
        Path filePath = Paths.get(this.filepath);
        try {
            Files.deleteIfExists(filePath);
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (String taskString : taskList.stringify()) {
                writer.write(taskString);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    // reading from duke.txt returns date as MMM dd yyyy (e.g Oct 15 2019) instead of
    // yyyy-mm-dd format (e.g 2019-10-15), hence this function converts
    // Oct 15 2019 to 2019-10-15
    public LocalDate formatDate(String inputDate) {
        return LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
    }
}
