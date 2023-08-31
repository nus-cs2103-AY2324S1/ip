package helpbuddy.storage;

import helpbuddy.exception.HelpBuddyException;
import helpbuddy.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }
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
                throw new IOException("Error occurred when reading data from storage file.");
        }

        if (Integer.parseInt(fields[1]) == 1) {
            taskToAdd.updateDone();
        }

        return taskToAdd;
    }

    private void createDatabase() throws IOException {
        File data = new File(this.filePath);
        File dataDir = new File(data.getParent());
        dataDir.mkdir();
        data.createNewFile();
    }

    public void saveData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            fw.write(taskList.getTask(i).stringifyTask());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
