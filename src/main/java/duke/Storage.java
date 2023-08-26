package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

import task.Task;
import task.Todos;
import task.Events;
import task.Deadlines;

public class Storage {

    private String outputPath;
    public Storage(String filePath) {
        this.outputPath = filePath;
    }

    /**
     * Creates the output file if does not exists. Also creates directories that are missing.
     *
     * @return File filePointer to output file
     */
    public File createOutputFile() {
        File filePointer = new File(this.outputPath);
        if (!filePointer.exists()) {
//            File directory = new File(System.getProperty("user.dir") + duke.Duke.dir);
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

    public ArrayList<Task> LoadOutputFile() throws FileNotFoundException{
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
                        arrList.add(new Todos(name, itemComplete));
                        break;
                    case "D":
                        String deadline = itemParts[3];
                        arrList.add(new Deadlines(name, deadline, itemComplete));
                        break;
                    case "E":
                        System.out.println(item);
                        String from = itemParts[3];
                        String to = itemParts[4];
                        arrList.add(new Events(name, from, to, itemComplete));
                        break;
                    default:
                        System.out.println("Error when reading file");
                }
            }
        }
        storageScanner.close();
        return arrList;
    }

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
