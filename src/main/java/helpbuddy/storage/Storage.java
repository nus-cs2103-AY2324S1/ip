package helpbuddy.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.task.Deadline;
import helpbuddy.task.Event;
import helpbuddy.task.Task;
import helpbuddy.task.TaskList;
import helpbuddy.task.ToDo;

/**
 * The Storage class that loads, reads and saves data. Creates a new file if file search is unsuccessful.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage under the specified filePath.
     * @param filePath the filePath with data stored.
     */
    public Storage(String filePath) {
        assert !filePath.trim().isEmpty() : "File path cannot be empty";
        this.filePath = filePath;
    }

    /**
     * Reads data from filePath and add Task from filePath to an ArrayList.
     * @return an ArrayList containing Task that is previously stored under the filePath.
     * @throws IOException if unable to read data from file or error creating a new file.
     * @throws HelpBuddyException if Task details in file are not present or invalid.
     */
    public ArrayList<Task> loadData() throws IOException, HelpBuddyException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File data = new File(filePath);
            Scanner fileReader = new Scanner(data);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                taskList.add(readEntry(entry));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            this.createDatabase();
        }
        return taskList;
    }

    /**
     * Reads each line under the filePath and creates a Task object corresponding to each line.
     * @param entry the entry under the filePath.
     * @return Task that is saved under the filePath.
     * @throws HelpBuddyException if Task details in file are not present or invalid.
     * @throws IOException if unable to read data from file.
     */
    public Task readEntry(String entry) throws HelpBuddyException, IOException {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        switch (fields[0]) {
        case "T":
            taskToAdd = new ToDo(fields[2]);
            break;
        case "D":
            taskToAdd = new Deadline(fields[2], LocalDateTime.parse(fields[3], formatter));
            break;
        case "E":
            String[] fromToFields = fields[3].split(" to ");
            taskToAdd = new Event(fields[2],
                    LocalDateTime.parse(fromToFields[0], formatter),
                    LocalDateTime.parse(fromToFields[1], formatter));
            break;
        default:
            assert false : "Unknown task in storage file";
            throw new IOException("Error occurred when reading data from storage file.\n "
                    + "Delete file and relaunch HelpBuddy again.");
        }

        if (Integer.parseInt(fields[1]) == 1) {
            taskToAdd.updateDone();
        }

        return taskToAdd;
    }

    /**
     * Creates a new file upon unsuccessful search of file under filePath.
     * @throws IOException if unable to create new file.
     */
    private void createDatabase() throws IOException {
        assert !this.filePath.trim().isEmpty() : "File path cannot be empty";
        File data = new File(this.filePath);
        File dataDir = new File(data.getParent());
        dataDir.mkdir();
        data.createNewFile();
    }

    /**
     * Writes all Task in TaskList into a file.
     * @param taskList the taskList with Task to be saved.
     * @throws IOException if unable to write to file.
     */
    public void saveData(TaskList taskList) throws IOException {
        assert !this.filePath.trim().isEmpty() : "File path cannot be empty";
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            fw.write(taskList.getTask(i).stringifyTask());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
