package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Encapsulates the Persistent data storage of the application.
 * Data is stored in a text file for convenience
 *
 * @author Donovan Chan Jia Jun
 */
public class Storage {

    private String outputPath;
    public Storage(String filePath) {
        this.outputPath = filePath;
    }

    /**
     * Creates the output file if does not exist. Also creates directories that are missing.
     *
     * @return File filePointer to output file
     */
    public File createOutputFile() {
        File filePointer = new File(this.outputPath);
        if (!filePointer.exists()) {
            File directory = new File(new File(this.outputPath).getParent());
            // create directory if it doesn't exist
            if (!directory.exists()) {
                boolean result = directory.mkdirs();
            }
            try {
                // create file in that directory
                if (!filePointer.createNewFile()) {
                    throw new FileNotFoundException();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filePointer;
    }

    /**
     * Loads the data from the Storage memory to the temporary Tasklist.
     *
     * @return ArrayList Contains the list of Task objects loaded from memory
     * @throws FileNotFoundException If no file is found at the filepath specified
     */
    public ArrayList<Task> loadOutputFile() throws FileNotFoundException {
        File filePointer = this.createOutputFile();
        Scanner storageScanner = new Scanner(filePointer);
        ArrayList<Task> arrList = new ArrayList<>();
        while (storageScanner.hasNext()) {
            String item = storageScanner.nextLine();
            if (item != "") {
                // process the item
                // T|1|read book
                String[] itemParts = item.split("\\|");
                boolean itemComplete = itemParts[1].equals("0");
                String name = itemParts[2];
                switch (itemParts[0]) {
                case "T":
                    arrList.add(new Todo(name, itemComplete));
                    break;
                case "D":
                    String deadline = itemParts[3];
                    arrList.add(new Deadline(name, deadline, itemComplete));
                    break;
                case "E":
                    System.out.println(item);
                    String from = itemParts[3];
                    String to = itemParts[4];
                    arrList.add(new Event(name, from, to, itemComplete));
                    break;
                default:
                    System.out.println("Error when reading file");
                }
            }
        }
        storageScanner.close();
        return arrList;
    }

    /**
     * Writes the data from TaskList to permanent storage at the file output location.
     *
     * @param taskList TaskList object that encapsulates the arraylist data structure, contains the updated tasks
     */
    public void updateTasks(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(outputPath, false);
            Consumer<Task> storeTask = task -> task.writeToFile(writer);
            taskList.getArrList().forEach(storeTask);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
