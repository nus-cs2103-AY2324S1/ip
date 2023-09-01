import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws ChatBuddyException {
        // load file from hard disk
        File file = new File(this.filePath);
        File parentDirectory = file.getParentFile();

        // check for existence of parentDirectory and file
        if (!parentDirectory.exists()) {
            parentDirectory.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new ChatBuddyException("Error creating data file.");
            }
        }

        // load data from file
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                // populate task array
                Task task = Parser.parseToTask(fileScanner.nextLine());
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new ChatBuddyException("Data file not found.");
        }

        return taskList;
    }

    public void save(TaskList tasks) throws ChatBuddyException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            ArrayList<String> taskStrings = tasks.getTaskStringsToSave();
            for (String taskString : taskStrings) {
                fileWriter.write(taskString + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new ChatBuddyException("Error saving data into file.");
        }

    }
}
